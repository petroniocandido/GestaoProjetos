/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.TextSearch;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.Indice;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Entidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ConfiguracaoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.Repositorio;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PositiveScoresOnlyCollector;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author petronio
 * @param <T>
 */
public abstract class IndiceGenerico<T extends Entidade> implements Indice<T> {

    Repositorio<T> dao;

    private IndexWriter writer;
    private Analyzer analyzer;

    private IndexSearcher reader;

    private QueryParser parser;

    private String pastaBase;

    private Directory directory;

    @EJB
    private ConfiguracaoRepositorio confDAO;

    protected abstract Document toDocument(T obj);

    protected abstract T toEntity(Document obj);

    @Override
    public abstract String getCaminho();

    @Override
    public abstract List<T> buscar(T obj);

    public IndiceGenerico() {
        analyzer = new BrazilianAnalyzer();
    }

    @Override
    public void indexacaoGeral() {
        try {
            for (T obj : dao.Buscar()) {
                indexar(obj);
            }

            fechaIndiceEscrita();
        } catch (IOException ex) {
            Logger.getLogger(IndiceGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void indexar(T obj) {
        try {

            if (writer == null) {
                abreIndiceEscrita();
            }

            indexar(toDocument(obj));
        } catch (IOException ex) {
            Logger.getLogger(IndiceGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<T> buscar(String campo, String expressao) {
        try {

            if (reader == null) {
                abreIndiceLeitura();
            }
            TopDocsCollector col = (TopDocsCollector) buscarIndice(campo, expressao);
            //TopDocs docs  = buscarIndice(campo, expressao);
            ScoreDoc[] docs = col.topDocs().scoreDocs;
            if (col.getTotalHits() > 0) {
                List<Long> tmp = new LinkedList<>();

                for (ScoreDoc d : docs) {
                    Document doc = abreDocumento(d);
                    Entidade e = toEntity(doc);
                    tmp.add(e.getId());
                }

                return dao.In(campo, tmp).Buscar();
            } else {
                return null;
            }
        } catch (IOException ex) {
            Logger.getLogger(IndiceGenerico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.apache.lucene.queryparser.classic.ParseException ex) {
            Logger.getLogger(IndiceGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Repositorio<T> getDao() {
        return dao;
    }

    public void setDao(Repositorio<T> dao) {
        this.dao = dao;
    }

    protected void abreIndiceEscrita() throws IOException {

        writer = new IndexWriter(getDirectory(), getWriterConfig());
    }
    protected IndexWriterConfig writerConfig;

    public IndexWriterConfig getWriterConfig() {
        if (writerConfig == null) {
            writerConfig = new IndexWriterConfig(analyzer);
            writerConfig.setCommitOnClose(true);
        }
        return writerConfig;
    }

    protected void fechaIndiceEscrita() throws CorruptIndexException, IOException {
        writer.close();
    }

    private void indexar(Document document) throws IOException {
        writer.addDocument(document);
        writer.commit();
    }

    protected void abreIndiceLeitura()
            throws IOException {
        IndexReader tmp = DirectoryReader.open(getDirectory());
        reader = new IndexSearcher(tmp);
    }

    protected Collector buscarIndice(String field, String searchQuery)
            throws IOException, org.apache.lucene.queryparser.classic.ParseException {
        parser = new QueryParser(field, analyzer);
        parser.setLocale(Locale.forLanguageTag("pt-BR"));
        Collector collector = TopFieldCollector.create(Sort.RELEVANCE, 10, true, true, true);

        Query query = parser.parse(searchQuery);
        //return reader.search(query, 100,Sort.RELEVANCE);
        //Query query = new TermQuery(new Term(field, searchQuery));

        //Query query = parser.createBooleanQuery(field, searchQuery);
        reader.search(query, collector);
        return collector;
    }

    public Document abreDocumento(ScoreDoc scoreDoc)
            throws CorruptIndexException, IOException {
        return reader.doc(scoreDoc.doc);
    }

    public String getPastaBase() {
        if (pastaBase == null) {
            pastaBase = confDAO.Abrir("DIRETORIO_INDICE").getValor();
        }

        return pastaBase;
    }

    public Directory getDirectory() throws IOException {
        if (directory == null) {
            directory = FSDirectory.open(Paths.get(getCaminho()));
        }
        return directory;
    }

}

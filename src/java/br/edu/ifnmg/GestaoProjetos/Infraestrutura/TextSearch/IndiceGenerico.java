/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.TextSearch;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.Repositorio;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author petronio
 */
public abstract class IndiceGenerico<T> implements Indice<T> {

    Repositorio<T> dao;

    private IndexWriter writer;
    private Analyzer analyzer;

    private IndexSearcher indexSearcher;

    private Query query;
    private QueryParser parser;
    
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
    public void indexar(T obj) {
        try {
            indexar(toDocument(obj));
        } catch (IOException ex) {
            Logger.getLogger(IndiceGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   

    @Override
    public List<T> buscar(String campo, String expressao) {
        try {
            buscarIndice(expressao, campo);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(IndiceGenerico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
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
        Directory indexDirectory = FSDirectory.open(new File(getCaminho()).toPath());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        
        writer = new IndexWriter(indexDirectory, config);
    }

    protected void fechaIndiceEscrita() throws CorruptIndexException, IOException {
        writer.close();
    }

    private void indexar(Document document) throws IOException {
        writer.addDocument(document);
    }

    protected void abreIndiceLeitura()
            throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(getCaminho())));
        indexSearcher = new IndexSearcher(reader);
    }

    protected TopDocs buscarIndice(String searchQuery, String field)
            throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
        parser = new QueryParser(field, analyzer);
        query = parser.parse(searchQuery);

        return indexSearcher.search(query, -1);
    }

    public Document abreDocumento(ScoreDoc scoreDoc)
            throws CorruptIndexException, IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }

}

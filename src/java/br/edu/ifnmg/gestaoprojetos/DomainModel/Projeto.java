/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Projeto implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //identificação do projeto
    private String titulo;

    @Column(unique = true) //vai ser unique??
    private int numeroCadastro;

    //informaÃ§Ãµes adicionais
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<AreaConhecimento> areaConhecimento;

    private boolean grupoPesquisa;

    private String nomegrupoPesquisa;

    @ManyToOne  //VERIFICAR
    private Campus campus;

    @ManyToOne
    private Edital edital;

    @ManyToOne  //VERIFICAR
    private Modalidade modalidade;

    @ManyToOne
    private AgenciaFinanciadora agenciaFinanciadora;

    //Resumo do projeto
    @Lob
    private String resumo;

    @Column(length = 500)
    private String palavrasChave;

    //DuraÃ§Ã£o do projeto
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataTermino;

    //CoordenaÃ§Ã£o do projeto
    @ManyToOne(optional = false)
    private Orientador coordenador;

    private String setorCoordenador;

    //Documentos
    @OneToMany(cascade = CascadeType.ALL)
    private List<Documento> documentos;

    // Plano de Trabalho 
    private String localRealizacaoProjeto; //laboratorio, sala, etc

    @Lob
    private String programaPlano; //programa a que o plano esta¡ envolvido

    @Lob
    private String introducao; //problema a ser estudado;

    @Lob
    private String justificativa;

    @Lob
    private String objetivos;

    @Lob
    private String metodologia;

    @Lob
    private String resutadosEsperados;

    @Lob
    private String referenciasBibliograficas;

    //Cronograma de Atividade
    @OneToMany(cascade = CascadeType.ALL)
    private List<Atividade> cronogramaAtividade;

    //Financiamento/Iniciacao cientifica
    private boolean projetoFinanciamento;

    private BigInteger valorFinanciamento;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinanciamento;

    private boolean bolsaIniciacaoCientifica;

    private int numeroBolsas;

    //ConvÃªnio/GestÃ£o
    private boolean projetoConvenio;

    private String nomeConvenio;

    private boolean projetoFundacao;

    private String nomeProjetoFundacao;

    private boolean projetoMulticampi;

    //IdentificaÃ§Ã£o dos Participantes dos Projetos
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Aluno> orientandos;

    public Projeto() {
        this.areaConhecimento = new ArrayList<>();
        this.orientandos = new ArrayList<>();
        this.documentos = new ArrayList<>();
        this.cronogramaAtividade = new ArrayList<>();
    }

    public void addAreaConhecimento(AreaConhecimento a) {
        if (areaConhecimento == null) {
            areaConhecimento = new ArrayList<>();
        }
        if (!areaConhecimento.contains(a)) {
            areaConhecimento.add(a);
        }

    }

    public void removeAreaConhecimento(AreaConhecimento a) {
        if (areaConhecimento.contains(a)) {
            areaConhecimento.remove(a);
        }
    }

    public void addAluno(Aluno a) {
        if (orientandos == null) {
            orientandos = new ArrayList<Aluno>();
        }
        if (!orientandos.contains(a)) {
            orientandos.add(a);
        }

    }

    public void removeAluno(Aluno a) {
        if (orientandos.contains(a)) {
            orientandos.remove(a);
        }
    }

    public void addDocumento(Documento d) {
        if (documentos == null) {
            documentos = new ArrayList<Documento>();
        }
        if (!documentos.contains(d)) {
            documentos.add(d);
        }
    }

    public void removeDocumento(Documento d) {
        if (documentos.contains(d)) {
            documentos.remove(d);
        }
    }

    public void addAtividade(Atividade a) {
        if (cronogramaAtividade == null) {
            cronogramaAtividade = new ArrayList<Atividade>();
        }
        if (!cronogramaAtividade.contains(a)) {
            cronogramaAtividade.add(a);
        }
    }

    public void removeAtividade(Atividade a) {
        if (cronogramaAtividade.contains(a)) {
            cronogramaAtividade.remove(a);
        }
    }

     //GETTER E SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroCadastro() {
        return numeroCadastro;
    }

    public void setNumeroCadastro(int numeroCadastro) {
        this.numeroCadastro = numeroCadastro;
    }

    public List<AreaConhecimento> getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(List<AreaConhecimento> areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public boolean isGrupoPesquisa() {
        return grupoPesquisa;
    }

    public void setGrupoPesquisa(boolean grupoPesquisa) {
        this.grupoPesquisa = grupoPesquisa;
    }

    public String getNomegrupoPesquisa() {
        return nomegrupoPesquisa;
    }

    public void setNomegrupoPesquisa(String nomegrupoPesquisa) {
        this.nomegrupoPesquisa = nomegrupoPesquisa;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Orientador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Orientador coordenador) {
        this.coordenador = coordenador;
    }

    public String getSetorCoordenador() {
        return setorCoordenador;
    }

    public void setSetorCoordenador(String setorCoordenador) {
        this.setorCoordenador = setorCoordenador;
    }

    public boolean isProjetoFinanciamento() {
        return projetoFinanciamento;
    }

    public void setProjetoFinanciamento(boolean projetoFinanciamento) {
        this.projetoFinanciamento = projetoFinanciamento;
    }

    public BigInteger getValorFinanciamento() {
        return valorFinanciamento;
    }

    public void setValorFinanciamento(BigInteger valorFinanciamento) {
        this.valorFinanciamento = valorFinanciamento;
    }

    public Date getDataFinanciamento() {
        return dataFinanciamento;
    }

    public void setDataFinanciamento(Date dataFinanciamento) {
        this.dataFinanciamento = dataFinanciamento;
    }

    public boolean isBolsaIniciacaoCientifica() {
        return bolsaIniciacaoCientifica;
    }

    public void setBolsaIniciacaoCientifica(boolean bolsaIniciacaoCientifica) {
        this.bolsaIniciacaoCientifica = bolsaIniciacaoCientifica;
    }

    public int getNumeroBolsas() {
        return numeroBolsas;
    }

    public void setNumeroBolsas(int numeroBolsas) {
        this.numeroBolsas = numeroBolsas;
    }

    public AgenciaFinanciadora getAgenciaFinanciadora() {
        return agenciaFinanciadora;
    }

    public void setAgenciaFinanciadora(AgenciaFinanciadora agenciaFinanciadora) {
        this.agenciaFinanciadora = agenciaFinanciadora;
    }

    public boolean isProjetoConvenio() {
        return projetoConvenio;
    }

    public void setProjetoConvenio(boolean projetoConvenio) {
        this.projetoConvenio = projetoConvenio;
    }

    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setNomeConvenio(String nomeConvenio) {
        this.nomeConvenio = nomeConvenio;
    }

    public boolean isProjetoFundacao() {
        return projetoFundacao;
    }

    public void setProjetoFundacao(boolean projetoFundacao) {
        this.projetoFundacao = projetoFundacao;
    }

    public String getNomeProjetoFundacao() {
        return nomeProjetoFundacao;
    }

    public void setNomeProjetoFundacao(String nomeProjetoFundacao) {
        this.nomeProjetoFundacao = nomeProjetoFundacao;
    }

    public boolean isProjetoMulticampi() {
        return projetoMulticampi;
    }

    public void setProjetoMulticampi(boolean projetoMulticampi) {
        this.projetoMulticampi = projetoMulticampi;
    }

    public List<Aluno> getOrientandos() {
        return orientandos;
    }

    public void setOrientandos(List<Aluno> orientandos) {
        this.orientandos = orientandos;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public String getLocalRealizacaoProjeto() {
        return localRealizacaoProjeto;
    }

    public void setLocalRealizacaoProjeto(String localRealizacaoProjeto) {
        this.localRealizacaoProjeto = localRealizacaoProjeto;
    }

    public String getProgramaPlano() {
        return programaPlano;
    }

    public void setProgramaPlano(String programaPlano) {
        this.programaPlano = programaPlano;
    }

    public String getIntroducao() {
        return introducao;
    }

    public void setIntroducao(String introducao) {
        this.introducao = introducao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getResutadosEsperados() {
        return resutadosEsperados;
    }

    public void setResutadosEsperados(String resutadosEsperados) {
        this.resutadosEsperados = resutadosEsperados;
    }

    public String getReferenciasBibliograficas() {
        return referenciasBibliograficas;
    }

    public void setReferenciasBibliograficas(String referenciasBibliograficas) {
        this.referenciasBibliograficas = referenciasBibliograficas;
    }

    public List<Atividade> getCronogramaAtividade() {
        return cronogramaAtividade;
    }

    public void setCronogramaAtividade(List<Atividade> cronogramaAtividade) {
        this.cronogramaAtividade = cronogramaAtividade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + this.numeroCadastro;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projeto other = (Projeto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.numeroCadastro != other.numeroCadastro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa criador;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa ultimoAlterador;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimaAlteracao;

    @Version
    private Long versao;

    @Override
    public Pessoa getCriador() {
        return criador;
    }

    @Override
    public void setCriador(Pessoa criador) {
        this.criador = criador;
    }

    @Override
    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public Pessoa getUltimoAlterador() {
        return ultimoAlterador;
    }

    @Override
    public void setUltimoAlterador(Pessoa ultimoAlterador) {
        this.ultimoAlterador = ultimoAlterador;
    }

    @Override
    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    @Override
    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    @Override
    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }
}

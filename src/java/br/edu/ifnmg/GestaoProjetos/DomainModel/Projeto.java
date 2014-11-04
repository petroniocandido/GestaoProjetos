/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Isla Guedes
 */
@Entity
@Table(name="projetos")
@Cacheable(true)
public class Projeto implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1500)
    private String titulo;

    private int numeroCadastro;

    
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = AreaConhecimento.class)
    private List<AreaConhecimento> areaConhecimento;

    private boolean grupoPesquisa;

    private String nomegrupoPesquisa;

    @ManyToOne
    private Campus campus;

    @ManyToOne
    private Edital edital;

    @ManyToOne
    private Modalidade modalidade;

    @ManyToOne
    private AgenciaFinanciadora agenciaFinanciadora;

    //Resumo do projeto
    @Lob
    private String resumo;

    @Column(length = 500)
    private String palavrasChave;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataTermino;

    @ManyToOne
    private Orientador coordenador;

    private String setorCoordenador;

    //Documentos
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projeto")
    private List<Documento> documentos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projeto")
    private List<Orcamento> orcamento;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projeto")
    private List<Atividade> cronogramaAtividade;

    private boolean projetoFinanciamento;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorFinanciamento;

    @Temporal(TemporalType.DATE)
    private Date dataFinanciamento;

    private boolean bolsaIniciacaoCientifica;

    private int numeroBolsas;

    private boolean projetoConvenio;

    private String nomeConvenio;

    private boolean projetoFundacao;

    private String nomeProjetoFundacao;

    private boolean projetoMulticampi;
    
    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Aluno.class)
    private List<Aluno> orientandos;

    public Projeto() {
        this.areaConhecimento = new ArrayList<>();
        this.orientandos = new ArrayList<>();
        this.documentos = new ArrayList<>();
        this.cronogramaAtividade = new ArrayList<>();
        this.situacao = ProjetoSituacao.Cadastrado;
        this.status = Status.Pendente;
        this.valorFinanciamento = new BigDecimal("0.00");
    }
    
    @Enumerated(EnumType.STRING)
    private ProjetoSituacao situacao;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public void addAreaConhecimento(AreaConhecimento a) {
        if(a == null) return;
        if (!areaConhecimento.contains(a)) {
            areaConhecimento.add(a);
        }
    }

    public void removeAreaConhecimento(AreaConhecimento a) {
        if(a == null) return;
        if (areaConhecimento.contains(a)) {
            areaConhecimento.remove(a);
        }
    }

    public void addAluno(Aluno a) {
        if(a == null) return;
        if (!orientandos.contains(a)) {
            orientandos.add(a);
        }
    }

    public void removeAluno(Aluno a) {
        if(a == null) return;
        if (orientandos.contains(a)) {
            orientandos.remove(a);
        }
    }
    
    public void addOrcamento(Orcamento d) {
        if(d == null) return;
        if (!orcamento.contains(d)) {
            orcamento.add(d);
            valorFinanciamento = valorFinanciamento.add(d.getValorOrcado());
        }
    }

    public void removeOrcamento(Orcamento d) {
        if(d == null) return;
        if (orcamento.contains(d)) {
            orcamento.remove(d);
            valorFinanciamento = valorFinanciamento.subtract(d.getValorOrcado());
        }
    }

    public void addDocumento(Documento d) {
        if(d == null) return;
        if (!documentos.contains(d)) {
            documentos.add(d);
        }
    }

    public void removeDocumento(Documento d) {
        if(d == null) return;
        if (documentos.contains(d)) {
            documentos.remove(d);
        }
    }

    public void addAtividade(Atividade a) {
        if(a == null) return;
        if (!cronogramaAtividade.contains(a)) {
            cronogramaAtividade.add(a);
        }
    }

    public void removeAtividade(Atividade a) {
        if(a == null) return;
        if (cronogramaAtividade.contains(a)) {
            cronogramaAtividade.remove(a);
        }
    }
    
    
    private boolean verificarDocumentos(){
        Date hoje = new Date();
        for(Documento d : getDocumentos())
            if(d.getStatus() == Status.Pendente && d.getTipoDocumento().isObrigatorio())
                return false;
        return true;
    }
    
    private boolean verificarCronograma(){
        Date hoje = new Date();
        for(Atividade a : getCronogramaAtividade())
            if(a.getStatus() == Status.Pendente){                
                return false;
            }
        return true;
    }
    
    private boolean verificarOrcamento(){
        Date hoje = new Date();
        for(Orcamento o : getOrcamento())
            if(o.getStatus() == Status.Pendente){                
                return false;
            }
        return true;
    }

     //GETTER E SETTER
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProjetoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(ProjetoSituacao situacao) {
        this.situacao = situacao;
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

    public BigDecimal getValorFinanciamento() {
        return valorFinanciamento;
    }

    public void setValorFinanciamento(BigDecimal valorFinanciamento) {
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

    public Status getStatus() {
        // Analisar Documentos
        if(!verificarDocumentos() || !verificarCronograma() || !verificarOrcamento() )
            return Status.Pendente;
        return Status.Regular;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Orcamento> getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(List<Orcamento> orcamento) {
        this.orcamento = orcamento;
    }
        
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.titulo);
        hash = 23 * hash + this.numeroCadastro;
        hash = 23 * hash + Objects.hashCode(this.campus);
        hash = 23 * hash + Objects.hashCode(this.edital);
        hash = 23 * hash + Objects.hashCode(this.modalidade);
        hash = 23 * hash + Objects.hashCode(this.agenciaFinanciadora);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (this.numeroCadastro != other.numeroCadastro) {
            return false;
        }
        if (!Objects.equals(this.campus, other.campus)) {
            return false;
        }
        if (!Objects.equals(this.edital, other.edital)) {
            return false;
        }
        if (!Objects.equals(this.modalidade, other.modalidade)) {
            return false;
        }
        if (!Objects.equals(this.agenciaFinanciadora, other.agenciaFinanciadora)) {
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Projeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //identificação do projeto
    @Column(nullable=false)
    private String titulo; 
    
    private int numeroCadastro; 
    
    //informações adicionais
    @ManyToMany //VERIFICAR
    private List<AreaConhecimento> areaConhecimento;
    
    private boolean grupoPesquisa;
    
    @Column(nullable=false)
    private String nomegrupoPesquisa;
    
    @ManyToOne  //VERIFICAR
    private Campus campus;
    
    //Resumo do projeto
    @Lob
    @Column(nullable=false)
    private String resumo;
    
    @Column(nullable=false, length=500)
    private String palavrasChave;
    
    //Duração do projeto
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataTermino;
    
    //Coordenação do projeto
    @ManyToOne 
    @Column(nullable=false)
     private Orientador coordenador;
     
     @Column(nullable=false)
     private String setorCoordenador;
     
     //Financiamento/Iniciação científica
     private boolean projetoFinanciamento;
     
     @Column(nullable=false)
     private BigInteger valorFinanciamento;
     
     @Temporal(javax.persistence.TemporalType.DATE)
     @Column(nullable=false)
     private Date dataFinanciamento;
     
     private boolean bolsaIniciacaoCientifica;
     
     @Column(nullable=false)
     private int numeroBolsas;
     
     @ManyToOne     
     private AgenciaFinanciadora agenciaFinanciadora; 
     
     //Convênio/Gestão
     private boolean projetoConvenio;
     
     @Column(nullable=false)
     private String nomeConvenio;
     
     private boolean projetoFundacao;
     
     @Column(nullable=false)
     private String nomeProjetoFundacao;
     
     private boolean projetoMulticampi;
     
     //Identificação dos Participantes dos Projetos
     @ManyToMany
     private List<Usuario> participantesProjeto;   
     
     @ManyToOne
     private Edital edital;
     
     
     
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

    public List<Usuario> getParticipantesProjeto() {
        return participantesProjeto;
    }

    public void setParticipantesProjeto(List<Usuario> participantesProjeto) {
        this.participantesProjeto = participantesProjeto;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
     
     
     
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Projeto{" + "id=" + id + '}';
    }

  
}

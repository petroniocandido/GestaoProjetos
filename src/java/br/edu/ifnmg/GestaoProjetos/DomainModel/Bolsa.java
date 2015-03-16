/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author petronio
 */
@Entity
@Table(name = "bolsas")
@Cacheable(false)
public class Bolsa implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Projeto projeto;

    @ManyToOne
    private Edital edital;

    @ManyToOne
    private Modalidade modalidade;

    @ManyToOne
    private AgenciaFinanciadora agenciaFinanciadora;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataTermino;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Documento.class)
    private List<Documento> documentos;

    @OrderBy("dataInicio asc, dataFim asc")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bolsa", targetEntity = Atividade.class)
    private List<Atividade> cronograma;

    @OrderBy("dataInicio desc, dataFim desc")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bolsa", targetEntity = AtividadeAcompanhamento.class)
    private List<AtividadeAcompanhamento> acompanhamentoAtividades;

    @ManyToOne
    private Aluno orientando;
    
    @Enumerated(EnumType.STRING)
    private ProjetoSituacao situacao;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public Bolsa() {
        this.situacao = ProjetoSituacao.Cadastrado;
        this.status = Status.Pendente;
        this.documentos = new ArrayList<>();
        this.cronograma = new ArrayList<>();
        this.acompanhamentoAtividades = new ArrayList<>();
    }
    
    public void addAtividade(Atividade a) {
        if(a == null) return;
        a.setBolsa(this);
        if (!cronograma.contains(a)) {
            cronograma.add(a);
        }
    }

    public void removeAtividade(Atividade a) {
        if(a == null) return;
        if (cronograma.contains(a)) {
            cronograma.remove(a);
        }
    }
       
    public void addAtividadeAcompanhamento(AtividadeAcompanhamento a) {
        if(a == null) return;
        a.setBolsa(this);
        if (!acompanhamentoAtividades.contains(a)) {
            acompanhamentoAtividades.add(a);
        }
    }

    public void removeAtividadeAcompanhamento(AtividadeAcompanhamento a) {
        if(a == null) return;
        if (acompanhamentoAtividades.contains(a)) {
            acompanhamentoAtividades.remove(a);
        }
    }
    
    public void addDocumento(Documento d) {
        if(d == null) return;
        if (!documentos.contains(d)) {
            d.setProjeto(this.projeto);
            documentos.add(d);
        }
    }

    public void removeDocumento(Documento d) {
        if(d == null) return;
        if (documentos.contains(d)) {
            documentos.remove(d);
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
     public AgenciaFinanciadora getAgenciaFinanciadora() {
        return agenciaFinanciadora;
    }

    public void setAgenciaFinanciadora(AgenciaFinanciadora agenciaFinanciadora) {
        this.agenciaFinanciadora = agenciaFinanciadora;
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
    
    public List<Atividade> getCronogramaAtividade() {
        return cronograma;
    }

    public void setCronogramaAtividade(List<Atividade> cronogramaAtividade) {
        this.cronograma = cronogramaAtividade;
    }
    
    public Status getStatus() {
        // Analisar Documentos
        if(!verificarDocumentos() || !verificarCronograma())
            return Status.Pendente;
        return Status.Regular;
    }
    
    public List<AtividadeAcompanhamento> getAcompanhamentoAtividades() {
        return acompanhamentoAtividades;
    }

    public void setAcompanhamentoAtividades(List<AtividadeAcompanhamento> acompanhamentoAtividades) {
        this.acompanhamentoAtividades = acompanhamentoAtividades;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
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

    public Aluno getOrientando() {
        return orientando;
    }

    public void setOrientando(Aluno orientando) {
        this.orientando = orientando;
    }

    public ProjetoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(ProjetoSituacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.projeto);
        hash = 79 * hash + Objects.hashCode(this.modalidade);
        hash = 79 * hash + Objects.hashCode(this.agenciaFinanciadora);
        hash = 79 * hash + Objects.hashCode(this.dataInicio);
        hash = 79 * hash + Objects.hashCode(this.dataTermino);
        hash = 79 * hash + Objects.hashCode(this.orientando);
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
        final Bolsa other = (Bolsa) obj;
        if (!Objects.equals(this.projeto, other.projeto)) {
            return false;
        }
        if (!Objects.equals(this.modalidade, other.modalidade)) {
            return false;
        }
        if (!Objects.equals(this.agenciaFinanciadora, other.agenciaFinanciadora)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataTermino, other.dataTermino)) {
            return false;
        }
        if (!Objects.equals(this.orientando, other.orientando)) {
            return false;
        }
        return true;
    }
           
    @Override
    public String toString() {
        return "br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa[ id=" + id + " ]";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author petronio
 */
@Entity
@Table(name="orcamentos")
@Cacheable(false)
public class Orcamento implements Entidade, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Projeto projeto;

    @Lob
    @Column(nullable = false)
    private String descricao;
    
    private int quantidadeOrcada;
    
    private int quantidadeExecutada;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPrevista;
    
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valorOrcado;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal valorUnitarioOrcado;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal valorExecutado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcamento")
    private List<OrcamentoExecucao> execucoes;

    public Orcamento() {
        valorOrcado = new BigDecimal("0.00");
        valorUnitarioOrcado = new BigDecimal("0.00");
        valorExecutado = new BigDecimal("0.00");
        execucoes = new ArrayList<>();
    }
    
    public Status getStatus() {
        if(getSaldo().compareTo(BigDecimal.ZERO) != 0 )
            return Status.Pendente;
        return Status.Regular;
    }
    
    public BigDecimal getSaldo() {
        if(valorOrcado != null && valorExecutado != null)
            return valorOrcado.subtract(valorExecutado);
        else
            return BigDecimal.ZERO;
    }
    
    public void add(OrcamentoExecucao e){
        if(getSaldo().compareTo(e.getValorTotal()) < 0)
            return;
        
        e.setOrcamento(this);
        if(!execucoes.contains(e)){
            execucoes.add(e);
            valorExecutado = valorExecutado.add(e.getValorTotal());
            quantidadeExecutada =+ e.getQuantidade();
        }
    }
    
    public void remove(OrcamentoExecucao e){
        if(execucoes.contains(e)){
            e.setOrcamento(null);
            execucoes.remove(e);
            valorExecutado = valorExecutado.subtract(e.getValorTotal());
            quantidadeExecutada =- e.getQuantidade();
        }
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public BigDecimal getValorOrcado() {
        return valorOrcado;
    }

    public void setValorOrcado(BigDecimal valorOrcado) {
        this.valorOrcado = valorOrcado;
    }

    public BigDecimal getValorExecutado() {
        return valorExecutado;
    }

    public void setValorExecutado(BigDecimal valorExecutado) {
        this.valorExecutado = valorExecutado;
    }

    public int getQuantidadeOrcada() {
        return quantidadeOrcada;
    }

    public void setQuantidadeOrcada(int quantidadeOrcada) {
        this.quantidadeOrcada = quantidadeOrcada;
    }

    public int getQuantidadeExecutada() {
        return quantidadeExecutada;
    }

    public void setQuantidadeExecutada(int quantidadeExecutada) {
        this.quantidadeExecutada = quantidadeExecutada;
    }

    public BigDecimal getValorUnitarioOrcado() {
        return valorUnitarioOrcado;
    }

    public void setValorUnitarioOrcado(BigDecimal valorUnitarioOrcado) {
        this.valorUnitarioOrcado = valorUnitarioOrcado;
    }

    public List<OrcamentoExecucao> getExecucoes() {
        return execucoes;
    }

    public void setExecucoes(List<OrcamentoExecucao> execucoes) {
        this.execucoes = execucoes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.projeto);
        hash = 37 * hash + Objects.hashCode(this.descricao);
        hash = 37 * hash + Objects.hashCode(this.valorOrcado);
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
        final Orcamento other = (Orcamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.projeto, other.projeto)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.valorOrcado, other.valorOrcado)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return descricao;
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

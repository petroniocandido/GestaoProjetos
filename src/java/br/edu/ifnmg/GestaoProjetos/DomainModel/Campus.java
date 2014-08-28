/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Isla Guedes
 */
@Entity
@Table(name="campus")
@Cacheable(true)
public class Campus implements Entidade, Serializable {
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String nome;
    
    @Column(nullable=false, unique = true)
    private String sigla;
    
    @ManyToOne(cascade= CascadeType.ALL)
    private Endereco endereco;    

    public Campus() {
        this.nome = "";
        this.endereco = new Endereco();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        if (!(object instanceof Campus)) {
            return false;
        }
        Campus other = (Campus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
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

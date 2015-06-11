/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import br.edu.ifnmg.DomainModel.Entidade;
import br.edu.ifnmg.DomainModel.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author petronio
 */
@Cacheable(true)
@Entity
@Table(name = "pessoasprojetos")
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy= InheritanceType.JOINED)
public class PessoaProjeto extends Pessoa implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "DTYPE")
    protected UsuarioTipo usuarioTipo;
   
    private String rg;
    
    private String orgaoExpeditor;
    
    @Temporal(TemporalType.DATE)    
    private Date dataExpedicao;
    
    @Column(length=2)
    private String naturalidadeUF;
    
    private String titulacao;
    
    private String observacao;//VER SER É NECESSARIO
    
    
    //Verificar RELACIONAMENTOS    
    @ManyToOne 
    private Campus campus;
    
    @ManyToMany(cascade= CascadeType.ALL) 
    private List<Endereco> enderecos;  
    
    @ManyToMany(cascade= CascadeType.ALL) 
    private List<Telefone> telefones;
    
    @ManyToMany(cascade= CascadeType.ALL) 
    private List<Email> emails;
    
    @Enumerated(EnumType.STRING)
    private Estados estado;      
    
    private String nacionalidade;   
    
    public PessoaProjeto() {
        id = 0L;
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        this.emails = new ArrayList<>();
        usuarioTipo = UsuarioTipo.Pessoa;
    }
            

    public void addTelefone(Telefone t){
        if(!telefones.contains(t)){
            telefones.add(t);
        }
        
    }
    
    public void removeTelefone(Telefone t){
        if(telefones.contains(t)){
            telefones.remove(t);
        }
    }
    
    public void addEmail(Email e){
        if(!emails.contains(e)){
            emails.add(e);
        }
        
    }
    
    public void removeEmail(Email e){
        if(emails.contains(e)){
            emails.remove(e);
        }
    }
    
     public void addEndereco(Endereco e){         
        if(!enderecos.contains(e)){
            enderecos.add(e);
        }
        
    }
    
    public void removeEndereco(Endereco e){
        if(enderecos.contains(e)){
            enderecos.remove(e);
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    

    public String getOrgaoExpeditor() {
        return orgaoExpeditor;
    }

    public void setOrgaoExpeditor(String orgaoExpeditor) {
        this.orgaoExpeditor = orgaoExpeditor;
    }

    public Date getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(Date dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public String getNaturalidadeUF() {
        return naturalidadeUF;
    }

    public void setNaturalidadeUF(String naturalidadeUF) {
        this.naturalidadeUF = naturalidadeUF;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
   

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> endereco) {
        this.enderecos = endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefone) {
        this.telefones = telefone;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> email) {
        this.emails = email;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    
    public UsuarioTipo getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning  this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        PessoaProjeto other = (PessoaProjeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    @Override
    public void setVersao(Long versao) {
        this.versao = versao;
    }
}

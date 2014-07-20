/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Isla Guedes
 */
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Usuario implements Entidade, Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //Dados Aluno/Orientador    
  
    private String nome;
    
    private String rg;
    
    @Column(length=14, unique=true) 
    private String cpf;   //CPF LOGIN
    
    private String senha;
    
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
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
    private List<Endereco> endereco;  
    
    @ManyToMany(cascade= CascadeType.ALL) 
    private List<Telefone> telefone;
    
    @ManyToMany(cascade= CascadeType.ALL) 
    private List<Email> email;
    
    private String estado;      
    
    private String nacionalidade;   

    public Usuario() {
        this.endereco = new ArrayList<Endereco>();
        this.telefone = new ArrayList<Telefone>();
        this.email = new ArrayList<Email>();
    }

    
    public void addTelefone(Telefone t){
        if(telefone == null)
            telefone = new ArrayList<Telefone>();
        if(!telefone.contains(t)){
            telefone.add(t);
        }
        
    }
    
    public void removeTelefone(Telefone t){
        if(telefone.contains(t)){
            telefone.remove(t);
        }
    }
    
    public void addEmail(Email e){
        if(email == null)
            email = new ArrayList<Email>();
        if(!email.contains(e)){
            email.add(e);
        }
        
    }
    
    public void removeEmail(Email e){
        if(email.contains(e)){
            email.remove(e);
        }
    }
    
     public void addEndereco(Endereco e){         
          if(endereco == null)
            endereco = new ArrayList<Endereco>();
        if(!endereco.contains(e)){
            endereco.add(e);
        }
        
    }
    
    public void removeEndereco(Endereco e){
        if(endereco.contains(e)){
            endereco.remove(e);
        }
    }
    
    
  
   //GETTER E SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
   
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
  
    
}
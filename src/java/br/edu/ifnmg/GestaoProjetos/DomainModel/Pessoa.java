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
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author petronio
 */
@Cacheable(true)
@Entity
@Table(name = "pessoas", indexes = {
    @Index(columnList = "cpf"),
    @Index(columnList = "email")})
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Pessoa implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(nullable = false, length = 300)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 300)
    private String email;

    @Column(length = 11)
    private String telefone;

    @Column(nullable = false)
    private String senha;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToOne(fetch = FetchType.EAGER)
    private Perfil perfil;
    
    @Enumerated(EnumType.STRING)
    protected PessoaTipo tipo;
    
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
    
    private String estado;      
    
    private String nacionalidade;   
    
    public Pessoa() {
        id = 0L;
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        this.emails = new ArrayList<>();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Transient
    private String cpfFormatado;

    public String getCpf() {
        if (cpfFormatado == null) {
            if (cpf != null && cpf.length() == 11) {
                cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            }
        }
        return cpfFormatado;
    }

    public void setCpf(String cpf) {
        if (cpf != null) {
            this.cpf = cpf.replace(".", "").replace("-", "");
            cpfFormatado = null;
        }
    }

    @Transient
    private String telefoneFormatado;

    public String getTelefone() {
        if (telefoneFormatado == null) {
            if (telefone != null && telefone.length() == 10) {
                telefoneFormatado = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
            }
        }
        return telefoneFormatado;
    }

    public void setTelefone(String telefone) {
        if (telefone != null) {
            this.telefone = telefone.replace(" ", "").replace("(", "").replace(")", "").replace("-", "");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public PessoaTipo getTipo() {
        return tipo;
    }

    public void setTipo(PessoaTipo tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome + " [" + getEmail()+ "]";
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

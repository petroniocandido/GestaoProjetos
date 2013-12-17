/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Aluno extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Column(nullable=false, unique=true)
    private int matricula;   
    
    //Documentos   
    @Column(nullable=false)
    private String tituloEleitoral;
    
    @Column(nullable=false)
    private String secaoEleitoral;
    
    @Column(nullable=false)
    private String zonaEleitoral;
    
    @Column(nullable=false)
    private String situacaoMilitar;
    
    @Column(nullable=false)
    private String certidaoMilitar;
    
    @Column(nullable=false)
    private boolean trabalhoStatus;
    
    //Dados dos pais    
    @Column(nullable=false)
    private String nomePai;
    
    @Column(nullable=false)
    private String rgPai;
    
    @Column(nullable=false)
    private String orgaoExpedidorPai;
    
    @Column(length=14)
    private int cpfPai;
    
    @Column(nullable=false)
    private String nomeMae;
    
    @Column(nullable=false)
    private String rgMae;
    
    @Column(nullable=false)
    private String orgaoExpedidorMae;
    
    @Column(length=14)
    private int cpfMae;
   
    //Dados da Conta
    @Column(nullable=false)
    private String banco;
    
    @Column(nullable=false)
    private int contaBancaria;
    
    @Column(nullable=false)
    private int agencia;      
    
    //Verificar RELACIONAMENTOS 
    @ManyToOne 
    private Curso curso;  
    
    @ManyToMany 
    private List<LocalTrabalho> listaLocalTrabalho;  
    
    @OneToMany
    private List<PlanoTrabalho> planoTrabalho;
    
    //GETTER E SETTER

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getTituloEleitoral() {
        return tituloEleitoral;
    }

    public void setTituloEleitoral(String tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }

    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(String secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public String getSituacaoMilitar() {
        return situacaoMilitar;
    }

    public void setSituacaoMilitar(String situacaoMilitar) {
        this.situacaoMilitar = situacaoMilitar;
    }

    public String getCertidaoMilitar() {
        return certidaoMilitar;
    }

    public void setCertidaoMilitar(String certidaoMilitar) {
        this.certidaoMilitar = certidaoMilitar;
    }

    public boolean isTrabalhoStatus() {
        return trabalhoStatus;
    }

    public void setTrabalhoStatus(boolean trabalhoStatus) {
        this.trabalhoStatus = trabalhoStatus;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getRgPai() {
        return rgPai;
    }

    public void setRgPai(String rgPai) {
        this.rgPai = rgPai;
    }

    public String getOrgaoExpedidorPai() {
        return orgaoExpedidorPai;
    }

    public void setOrgaoExpedidorPai(String orgaoExpedidorPai) {
        this.orgaoExpedidorPai = orgaoExpedidorPai;
    }

    public int getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(int cpfPai) {
        this.cpfPai = cpfPai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getRgMae() {
        return rgMae;
    }

    public void setRgMae(String rgMae) {
        this.rgMae = rgMae;
    }

    public String getOrgaoExpedidorMae() {
        return orgaoExpedidorMae;
    }

    public void setOrgaoExpedidorMae(String orgaoExpedidorMae) {
        this.orgaoExpedidorMae = orgaoExpedidorMae;
    }

    public int getCpfMae() {
        return cpfMae;
    }

    public void setCpfMae(int cpfMae) {
        this.cpfMae = cpfMae;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(int contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<LocalTrabalho> getListaLocalTrabalho() {
        return listaLocalTrabalho;
    }

    public void setListaLocalTrabalho(List<LocalTrabalho> listaLocalTrabalho) {
        this.listaLocalTrabalho = listaLocalTrabalho;
    }

    public List<PlanoTrabalho> getPlanoTrabalho() {
        return planoTrabalho;
    }

    public void setPlanoTrabalho(List<PlanoTrabalho> planoTrabalho) {
        this.planoTrabalho = planoTrabalho;
    }
    

   
   

  
}

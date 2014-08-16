/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Orientador extends Pessoa implements Entidade, Serializable{
    private static final long serialVersionUID = 1L;
   
    @Column(unique=true)
    private int matriculaSiape;     
    
    private String localPermanencia;
    
    @ManyToMany(cascade= CascadeType.MERGE)
    private List<AreaConhecimento> areaConhecimento;
    
   //Dados de Formação     
    
    private String formacaoUniversitaria;
        
    private String tituloAcademico;    
    
    
   //Dados de Solicitação
    @OneToMany
     private List<Projeto> projetos;
    
    @OneToMany
    private List<Aluno> alunos;

    public Orientador() {
        this.areaConhecimento = new ArrayList<>();
    }

    public void addAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento == null) {
            areaConhecimento = new ArrayList<>();
        }
        if(!areaConhecimento.contains(a)){
            areaConhecimento.add(a);
        }
        
    }
    
    public void removeAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento.contains(a)){
            areaConhecimento.remove(a);
        }
    }
    
    //GETTER E SETTER

    public int getMatriculaSiape() {
        return matriculaSiape;
    }

    public void setMatriculaSiape(int matriculaSiape) {
        this.matriculaSiape = matriculaSiape;
    }

    public String getLocalPermanencia() {
        return localPermanencia;
    }

    public void setLocalPermanencia(String localPermanencia) {
        this.localPermanencia = localPermanencia;
    }

    public List<AreaConhecimento> getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(List<AreaConhecimento> areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public String getFormacaoUniversitaria() {
        return formacaoUniversitaria;
    }

    public void setFormacaoUniversitaria(String formacaoUniversitaria) {
        this.formacaoUniversitaria = formacaoUniversitaria;
    }

    public String getTituloAcademico() {
        return tituloAcademico;
    }

    public void setTituloAcademico(String tituloAcademico) {
        this.tituloAcademico = tituloAcademico;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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
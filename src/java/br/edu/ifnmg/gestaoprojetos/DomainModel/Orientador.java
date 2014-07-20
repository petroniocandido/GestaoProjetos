/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Orientador extends Usuario implements Entidade, Serializable{
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
        this.areaConhecimento = new ArrayList<AreaConhecimento>();
    }

    public void addAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento == null) {
            areaConhecimento = new ArrayList<AreaConhecimento>();
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
    
    
    
    
}
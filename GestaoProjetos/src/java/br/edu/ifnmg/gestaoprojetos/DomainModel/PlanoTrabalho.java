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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class PlanoTrabalho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    //Dados do Aluno    
    @ManyToOne
    private Aluno aluno;   
    
    //Plano de Trabalho
    @ManyToOne
    private Projeto projeto;
    
    @ManyToOne
    private Orientador orientador;
    
    @Column(nullable=false)
    private String localRealizacaoProjeto; //laboratório, sala, etc
    
    @Lob
    @Column(nullable=false)
    private String programaPlano; //rograma a que o plano está envolvido
    
    @Lob
    @Column(nullable=false)
    private String introducao; //problema a ser estudado;
   
    @Lob
    @Column(nullable=false)
    private String justificativa;
    
    @Lob
    @Column(nullable=false)
    private String objetivos;
    
    @Lob
    @Column(nullable=false)
    private String metodologia;
    
    @Lob
    @Column(nullable=false)
    private String resutadosEsperados;
    
    @Lob
    @Column(nullable=false)
    private String referenciasBibliograficas;
    
    
    //Cronograma de Atividade
    @OneToMany
    private List<Atividade> cronogramaAtividade;
    
    
    // GETTER E SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public String getLocalRealizacaoProjeto() {
        return localRealizacaoProjeto;
    }

    public void setLocalRealizacaoProjeto(String localRealizacaoProjeto) {
        this.localRealizacaoProjeto = localRealizacaoProjeto;
    }

    public String getProgramaPlano() {
        return programaPlano;
    }

    public void setProgramaPlano(String programaPlano) {
        this.programaPlano = programaPlano;
    }

    public String getIntroducao() {
        return introducao;
    }

    public void setIntroducao(String introducao) {
        this.introducao = introducao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getResutadosEsperados() {
        return resutadosEsperados;
    }

    public void setResutadosEsperados(String resutadosEsperados) {
        this.resutadosEsperados = resutadosEsperados;
    }

    public String getReferenciasBibliograficas() {
        return referenciasBibliograficas;
    }

    public void setReferenciasBibliograficas(String referenciasBibliograficas) {
        this.referenciasBibliograficas = referenciasBibliograficas;
    }

    public List<Atividade> getCronogramaAtividade() {
        return cronogramaAtividade;
    }

    public void setCronogramaAtividade(List<Atividade> cronogramaAtividade) {
        this.cronogramaAtividade = cronogramaAtividade;
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
        if (!(object instanceof PlanoTrabalho)) {
            return false;
        }
        PlanoTrabalho other = (PlanoTrabalho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanoTrabalho{" + "id=" + id + '}';
    }

   
    
}

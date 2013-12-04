/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domainModel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long idPlanoTrabalho; 
    
    //Dados do Aluno    
    @OneToMany
    private List<Aluno> aluno;   
    
    //Plano de Trabalho
    @OneToMany
    private List<Projeto> projeto;
    
    @OneToMany
    private List<Orientador> orientador;
    
    @Column(nullable=false)
    private String localRealizacaoProjeto; //laboratório, sala, etc
    
    @Column(nullable=false)
    private String programaPlano; //programa a que o plano está envolvido
    
    @Column(nullable=false)
    private String introducao; //problema a ser estudado;
    
    @Column(nullable=false)
    private String justificativa;
    
    @Column(nullable=false)
    private String objetivos;
    
    @Column(nullable=false)
    private String metodologia;
    
    @Column(nullable=false)
    private String resutadosEsperados;
    
    @Column(nullable=false)
    private String referenciasBibliograficas;
    
    
    //Cronograma de Atividade
    @OneToMany
    private List<CronogramaAtividade> cronogramaAtividade;
    
    
    
    //FALTA GETTER E SETTER
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanoTrabalho != null ? idPlanoTrabalho.hashCode() : 0);
        return hash;
    }

    public Long getIdPlanoTrabalho() {
        return idPlanoTrabalho;
    }

    public void setIdPlanoTrabalho(Long idPlanoTrabalho) {
        this.idPlanoTrabalho = idPlanoTrabalho;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanoTrabalho)) {
            return false;
        }
        PlanoTrabalho other = (PlanoTrabalho) object;
        if ((this.idPlanoTrabalho == null && other.idPlanoTrabalho != null) || (this.idPlanoTrabalho != null && !this.idPlanoTrabalho.equals(other.idPlanoTrabalho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.PlanoTrabalho[ id=" + idPlanoTrabalho + " ]";
    }
    
}

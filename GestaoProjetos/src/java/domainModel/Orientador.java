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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Orientador extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
   
    @Column(nullable=false)
    private int matriculaSiape;  
    
    @Column(nullable=false)
    private String localPermanencia;
    
    @ManyToMany
    private List<AreaConhecimento> areaConhecimento;
    
   //Dados de Formação     
    @Column(nullable=false)
    private String formacaoUniversitaria;
    
    @Column(nullable=false)
    private String tituloAcademico;    
    
    private boolean status;
    
   //Dados de Solicitação
    @OneToMany
     private List<Projeto> projetos;
    
    @OneToMany
    private List<Aluno> alunos;
   
    
}

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Aluno extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Column(nullable=false)
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
    

    // FALTA  GETTER E SETTER
   

  
}

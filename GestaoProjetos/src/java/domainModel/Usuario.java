/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Usuario{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    
    //Dados Aluno/Orientador    
    @Column(nullable=false)
    private String nome;
    
    @Column(nullable=false)  
    private String rg;
    
    @Column(length=14)
    private int cpf;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataNascimento;
    
    @Column(nullable=false)
    private String orgaoExpeditor;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataExpedicao;
    
    @Column(length=2)
    private String naturalidadeUF;
    
    @Column(nullable=false)
    private String titulação;
    
    private String observacao;
    
    private boolean status; //VER SER É NECESSARIO
    
    //Verificar RELACIONAMENTOS    
    @ManyToOne 
    private Campus campus;
    
    @ManyToMany 
    private List<Endereco> endereco;  
    
    @ManyToMany
    private List<Telefone> telefone;
    
    @ManyToMany
    private List<Email> email;
    
    @ManyToMany
    private Estado estado;  
    
    @ManyToMany
    private Nacionalidade nacionalidade;   
    
  
   // FALTA  GETTER E SETTER

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }
  
    @Override
    public String toString() {
        return "domainModel.Usuario[ id=" + idUsuario + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domainModel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Isla Guedes
 */

@Entity
public class Nacionalidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNacionalidade;
    
    @Column(nullable=false)  
    private String nacionalidade;
    
    private boolean status;
   

    public Long getIdNacionalidade() {
        return idNacionalidade;
    }

    public void setIdNacionalidade(Long idNacionalidade) {
        this.idNacionalidade = idNacionalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
      
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNacionalidade != null ? idNacionalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalidade)) {
            return false;
        }
        Nacionalidade other = (Nacionalidade) object;
        if ((this.idNacionalidade == null && other.idNacionalidade != null) || (this.idNacionalidade != null && !this.idNacionalidade.equals(other.idNacionalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Nacionalidade[ id=" + idNacionalidade + " ]";
    }
    
}

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
import javax.persistence.Temporal;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Edital implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEdital;
    
    @Column(nullable=false)
    private int numeroEdital;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private int dataExpedicaoEdital;
        
    

    public Long getIdEdital() {
        return idEdital;
    }

    public void setIdEdital(Long idEdital) {
        this.idEdital = idEdital;
    }

    public int getNumeroEdital() {
        return numeroEdital;
    }

    public void setNumeroEdital(int numeroEdital) {
        this.numeroEdital = numeroEdital;
    }

    public int getDataExpedicaoEdital() {
        return dataExpedicaoEdital;
    }

    public void setDataExpedicaoEdital(int dataExpedicaoEdital) {
        this.dataExpedicaoEdital = dataExpedicaoEdital;
    }
    
   
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEdital != null ? idEdital.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edital)) {
            return false;
        }
        Edital other = (Edital) object;
        if ((this.idEdital == null && other.idEdital != null) || (this.idEdital != null && !this.idEdital.equals(other.idEdital))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Edital[ id=" + idEdital + " ]";
    }
    
}

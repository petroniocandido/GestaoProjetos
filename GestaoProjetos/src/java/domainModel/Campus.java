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
import javax.persistence.OneToOne;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Campus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCampus;
    
    @Column(nullable=false)
    private String nomeCampus;
    
    @ManyToMany  //verificar se está correto
    private List<Endereco> endereco;    
    

    public Long getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(Long idCampus) {
        this.idCampus = idCampus;
    }

    
    //falta getter e setter
   
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampus != null ? idCampus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campus)) {
            return false;
        }
        Campus other = (Campus) object;
        if ((this.idCampus == null && other.idCampus != null) || (this.idCampus != null && !this.idCampus.equals(other.idCampus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Campus[ id=" + idCampus + " ]";
    }
    
}

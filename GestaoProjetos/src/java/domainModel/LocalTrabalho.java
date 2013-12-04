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
import javax.persistence.OneToOne;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class LocalTrabalho implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLocalTrabalho;
    
    @Column(nullable=false)
    private String localTrabalho;
    
    private boolean status;
    
    @OneToMany
    private List<Telefone> telefone;
   

    //getter e setter
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalTrabalho != null ? idLocalTrabalho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalTrabalho)) {
            return false;
        }
        LocalTrabalho other = (LocalTrabalho) object;
        if ((this.idLocalTrabalho == null && other.idLocalTrabalho != null) || (this.idLocalTrabalho != null && !this.idLocalTrabalho.equals(other.idLocalTrabalho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.LocalTrabalho[ id=" + idLocalTrabalho + " ]";
    }
    
}

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
public class AreaConhecimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAreaConhecimento;
    
    @Column(nullable=false)
    private String nomeAreaConhecimento;

    public Long getIdAreaConhecimento() {
        return idAreaConhecimento;
    }

    public void setIdAreaConhecimento(Long idAreaConhecimento) {
        this.idAreaConhecimento = idAreaConhecimento;
    }

    public String getNomeAreaConhecimento() {
        return nomeAreaConhecimento;
    }

    public void setNomeAreaConhecimento(String nomeAreaConhecimento) {
        this.nomeAreaConhecimento = nomeAreaConhecimento;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaConhecimento != null ? idAreaConhecimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaConhecimento)) {
            return false;
        }
        AreaConhecimento other = (AreaConhecimento) object;
        if ((this.idAreaConhecimento == null && other.idAreaConhecimento != null) || (this.idAreaConhecimento != null && !this.idAreaConhecimento.equals(other.idAreaConhecimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.AreaConhecimento[ id=" + idAreaConhecimento + " ]";
    }
    
}

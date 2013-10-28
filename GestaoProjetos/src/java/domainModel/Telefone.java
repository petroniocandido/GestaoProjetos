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
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTelefone;
    
    @Column(length=2) // ver lenght máximo
    private int ddd;
    @Column(nullable=false)
    private int numero;    
    private int status;  
    
    
   //Getter e Setter 

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefone != null ? idTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.idTelefone == null && other.idTelefone != null) || (this.idTelefone != null && !this.idTelefone.equals(other.idTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Telefone[ id=" + idTelefone + " ]";
    }
    
}

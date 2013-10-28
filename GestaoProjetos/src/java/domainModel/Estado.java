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
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEstado;

    @Column(nullable=false)
    private String nome;
    @Column(nullable=false,length=2)
    private String uf;
    private boolean status;

    //Getter e Setter

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Estado[ id=" + idEstado + " ]";
    }
    
}

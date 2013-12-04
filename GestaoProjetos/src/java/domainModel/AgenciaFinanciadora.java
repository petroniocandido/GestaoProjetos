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
public class AgenciaFinanciadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAgenciaFinanciadora;
    
    @Column(nullable=false)
    private String nomeAgenciaFinanciadora;
    
    @Column(nullable=false)
    private String siglaAgenciaFinanciadora;
    
    
    public Long getIdAgenciaFinanciadora() {
        return idAgenciaFinanciadora;
    }

    public void setIdAgenciaFinanciadora(Long idAgenciaFinanciadora) {
        this.idAgenciaFinanciadora = idAgenciaFinanciadora;
    }

    public String getNomeAgenciaFinanciadora() {
        return nomeAgenciaFinanciadora;
    }

    public void setNomeAgenciaFinanciadora(String nomeAgenciaFinanciadora) {
        this.nomeAgenciaFinanciadora = nomeAgenciaFinanciadora;
    }

    public String getSiglaAgenciaFinanciadora() {
        return siglaAgenciaFinanciadora;
    }

    public void setSiglaAgenciaFinanciadora(String siglaAgenciaFinanciadora) {
        this.siglaAgenciaFinanciadora = siglaAgenciaFinanciadora;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenciaFinanciadora != null ? idAgenciaFinanciadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgenciaFinanciadora)) {
            return false;
        }
        AgenciaFinanciadora other = (AgenciaFinanciadora) object;
        if ((this.idAgenciaFinanciadora == null && other.idAgenciaFinanciadora != null) || (this.idAgenciaFinanciadora != null && !this.idAgenciaFinanciadora.equals(other.idAgenciaFinanciadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.agenciaFinanciadora[ id=" + idAgenciaFinanciadora + " ]";
    }
    
}

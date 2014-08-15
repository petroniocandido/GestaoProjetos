/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "agenciaFinanciadoraConverter")
@SessionScoped
public class AgenciaFinanciadoraConverter implements Serializable, Converter  {
    

    /**
     * Creates a new instance of AgenciaFinanciadoraConverter
     */
    public AgenciaFinanciadoraConverter() {
    }
    
    @EJB
    AgenciaFinanciadoraRepositorio daoAgenciaFinanciadora;

    /**
     * Creates a new instance of LocalConverter
     */
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoAgenciaFinanciadora.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            AgenciaFinanciadora ag = (AgenciaFinanciadora) value;
            return ag.getId().toString();
        }
    }

}

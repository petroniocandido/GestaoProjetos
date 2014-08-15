/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Edital;
import br.edu.ifnmg.gestaoprojetos.DomainModel.EditalRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author HOME
 */
@Named(value = "editalConverter")
@SessionScoped
public class EditalConverter implements Serializable, Converter {

    /**
     * Creates a new instance of EditalConverter
     */
    public EditalConverter() {
    }
    
    @EJB
    EditalRepositorio daoEdital;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoEdital.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Edital e = (Edital) value;
            return e.getId().toString();
        }
    }

}

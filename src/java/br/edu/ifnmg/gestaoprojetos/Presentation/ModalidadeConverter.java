/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Modalidade;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ModalidadeRepositorio;
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
@Named(value = "modalidadeConverter")
@SessionScoped
public class ModalidadeConverter implements Serializable, Converter {

    /**
     * Creates a new instance of ModalidadeConverter
     */
    public ModalidadeConverter() {
    }
    
    @EJB
    ModalidadeRepositorio daoModalidade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoModalidade.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Modalidade m = (Modalidade) value;
            return m.getId().toString();
        }
    }


}

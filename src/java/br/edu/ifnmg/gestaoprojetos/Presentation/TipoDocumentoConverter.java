/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.TipoDocumento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.TipoDocumentoRepositorio;
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
@Named(value = "tipoDocumentoConverter")
@SessionScoped
public class TipoDocumentoConverter implements Serializable, Converter {

    /**
     * Creates a new instance of TipoDocumentoConverter
     */
    public TipoDocumentoConverter() {
    }
    
    @EJB
    TipoDocumentoRepositorio daoTipoDocumento;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoTipoDocumento.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString() == null || value.toString() == "") {
            return "";
        } else {
            TipoDocumento td = (TipoDocumento) value;
            return td.getId().toString();
        }
    }

}

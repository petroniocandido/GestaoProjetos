/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Curso;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Orientador;
import br.edu.ifnmg.gestaoprojetos.DomainModel.OrientadorRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "orientadorConverter")
@SessionScoped
public class OrientadorConverter implements Serializable,Converter {

    /**
     * Creates a new instance of OrientadorConverter
     */
    public OrientadorConverter() {
    }
    
    @EJB
    OrientadorRepositorio daoOrientador;
    
    public List<Orientador> autoCompleteOrientador(String query){
        Orientador filtro = new Orientador();
        filtro.setNome(query);
        return daoOrientador.Buscar(filtro);
    }
    
    /**
     * Creates a new instance of LocalConverter
     */
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoOrientador.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Orientador o = (Orientador) value;
            return o.getId().toString();
        }
    }

}

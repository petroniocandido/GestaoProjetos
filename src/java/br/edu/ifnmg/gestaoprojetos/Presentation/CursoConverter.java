/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Curso;
import br.edu.ifnmg.gestaoprojetos.DomainModel.CursoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "cursoConverter")
@SessionScoped
public class CursoConverter implements Serializable, Converter {

    /**
     * Creates a new instance of CursoConverter
     */
    public CursoConverter() {
    }
    
    @EJB
    CursoRepositorio daoCurso;

    /**
     * Creates a new instance of LocalConverter
     */
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoCurso.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Curso c = (Curso) value;
            return c.getId().toString();
        }
    }

}

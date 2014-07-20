/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Aluno;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import br.edu.ifnmg.gestaoprojetos.DomainModel.UsuarioRepositorio;
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
 * @author HOME
 */
@Named(value = "usuarioConverter")
@SessionScoped
public class UsuarioConverter implements Serializable,Converter {

    /**
     * Creates a new instance of UsuarioConverter
     */
    public UsuarioConverter() {
    }
    
    @EJB
    UsuarioRepositorio daoUsuario;
    
    public List<Usuario> autoCompleteUsuario(String query){
        Usuario filtro = new Usuario();
        filtro.setNome(query);
        return daoUsuario.Buscar(filtro);
    }
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoUsuario.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Usuario u = (Usuario) value;
            return u.getId().toString();
        }
    }

}

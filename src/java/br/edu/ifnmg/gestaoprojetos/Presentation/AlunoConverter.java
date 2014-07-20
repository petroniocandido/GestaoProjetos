/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Aluno;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AlunoRepositorio;
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
@Named(value = "alunoConverter")
@SessionScoped
public class AlunoConverter implements Serializable, Converter {

    /**
     * Creates a new instance of AlunoConverter
     */
    public AlunoConverter() {
    }
    
    @EJB
    AlunoRepositorio daoAluno;
    
    public List<Aluno> autoCompleteAluno(String query){
        Aluno filtro = new Aluno();
        filtro.setNome(query);
        return daoAluno.Buscar(filtro);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoAluno.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Aluno a = (Aluno) value;
            return a.getId().toString();
        }
    }
}

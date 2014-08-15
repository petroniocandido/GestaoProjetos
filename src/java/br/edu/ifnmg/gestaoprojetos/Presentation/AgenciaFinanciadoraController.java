/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadoraRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "agenciaFinanciadoraController")
@SessionScoped
public class AgenciaFinanciadoraController 
    extends ControllerGenerico<AgenciaFinanciadora> implements Serializable {
 
    
    /**
     * Creates a new instance of AgenciaFinanciadoraController
     */
    
    public AgenciaFinanciadoraController() {
        filtro = new AgenciaFinanciadora();
        entidade = new AgenciaFinanciadora();       
    }

    @EJB
    AgenciaFinanciadoraRepositorio dao;

    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            listagem = null;
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new AgenciaFinanciadora();
        return "editarAgenciaFinanciadora.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarAgenciaFinanciadora.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAgenciaFinanciadora.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemAgenciaFinanciadora.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public AgenciaFinanciadoraRepositorio getDao() {
        return dao;
    }

    public void setDao(AgenciaFinanciadoraRepositorio dao) {
        this.dao = dao;
    }
  
  
}

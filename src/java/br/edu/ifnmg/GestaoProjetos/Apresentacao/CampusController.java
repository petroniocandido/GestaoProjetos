/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "campusController")
@SessionScoped
public class CampusController
    extends ControllerGenerico<Campus> implements Serializable {

    /**
     * Creates a new instance of CampusController
     */
    public CampusController() {        
        filtro = new Campus();
        entidade = new Campus();        
    }
    
    @EJB
    CampusRepositorio dao;
    
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
        entidade = new Campus();
        return "editarCampus.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarCampus.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemCampus.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemCampus.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public CampusRepositorio getDao() {
        return dao;
    }

    public void setDao(CampusRepositorio dao) {
        this.dao = dao;
    }
    
    

}


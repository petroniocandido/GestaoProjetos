/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Edital;
import br.edu.ifnmg.gestaoprojetos.DomainModel.EditalRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "editalController")
@SessionScoped
public class EditalController 
    extends ControllerGenerico<Edital> implements Serializable {

    List<AgenciaFinanciadora> listagemAgencia;

    
    
    /**
     * Creates a new instance of EditalController
     */
    public EditalController() {        
        filtro = new Edital();
        entidade = new Edital();        
    }    
    
    @EJB
    EditalRepositorio dao;
    
    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;
    
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
        entidade = new Edital();
        return "editarEdital.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarEdital.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemEdital.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null; 
            return "listagemEdital.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public EditalRepositorio getDao() {
        return dao;
    }

    public void setDao(EditalRepositorio dao) {
        this.dao = dao;
    }
    
    public List<AgenciaFinanciadora> getListagemAgencia() {
        if (listagemAgencia == null) {
            Edital filtro = new Edital();
            listagemAgencia = daoAgencia.Buscar(null);
        }       
        
        return listagemAgencia;
    }

    public void setListagemAgencia(List<AgenciaFinanciadora> listagemAgencia) {
        this.listagemAgencia = listagemAgencia;
    }

   

}

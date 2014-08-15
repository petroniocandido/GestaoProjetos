/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "areaConhecimentoController")
@SessionScoped
public class AreaConhecimentoController
    extends ControllerGenerico<AreaConhecimento> implements Serializable {

    /**
     * Creates a new instance of AreaConhecimentoController
     */
    public AreaConhecimentoController() {
        filtro = new AreaConhecimento();
        entidade = new AreaConhecimento(); 
    }
    
    @EJB
    AreaConhecimentoRepositorio dao;
    
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
        entidade = new AreaConhecimento();
        return "editarAreaConhecimento.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarAreaConhecimento.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAreaConhecimento.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;  
            return "listagemAreaConhecimento.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public AreaConhecimentoRepositorio getDao() {
        return dao;
    }

    public void setDao(AreaConhecimentoRepositorio dao) {
        this.dao = dao;
    }
    
    

}


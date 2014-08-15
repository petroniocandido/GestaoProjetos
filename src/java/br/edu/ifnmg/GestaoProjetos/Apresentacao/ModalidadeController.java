/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author HOME
 */
@Named(value = "modalidadeController")
@SessionScoped
public class ModalidadeController 
    extends ControllerGenerico<Modalidade> implements Serializable {

    /**
     * Creates a new instance of ModalidadeController
     */
    public ModalidadeController() {
        filtro = new Modalidade();
        entidade = new Modalidade(); 
    }
    
    @EJB
    ModalidadeRepositorio dao;
    
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
        entidade = new Modalidade();
        return "editarModalidade.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarModalidade.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemModalidade.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemModalidade.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public ModalidadeRepositorio getDao() {
        return dao;
    }

    public void setDao(ModalidadeRepositorio dao) {
        this.dao = dao;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.TipoDocumento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.TipoDocumentoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author HOME
 */
@Named(value = "tipoDocumentoController")
@SessionScoped
public class TipoDocumentoController 
    extends ControllerGenerico<TipoDocumento> implements Serializable {

    /**
     * Creates a new instance of TipoDocumentoController
     */
    public TipoDocumentoController() {
        filtro = new TipoDocumento();
        entidade = new TipoDocumento();
    }
    
    @EJB
    TipoDocumentoRepositorio dao;
    
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
        entidade = new TipoDocumento();
        return "editarTipoDocumento.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarTipoDocumento.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemTipoDocumento.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;  
            return "listagemTipoDocumento.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public TipoDocumentoRepositorio getDao() {
        return dao;
    }

    public void setDao(TipoDocumentoRepositorio dao) {
        this.dao = dao;
    }
    
    

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.TipoDocumento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.TipoDocumentoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author HOME
 */
@Named(value = "tipoDocumentoController")
@RequestScoped
public class TipoDocumentoController 
    extends ControllerBaseEntidade<TipoDocumento> implements Serializable {

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
    public TipoDocumento getFiltro() {
        if (filtro == null) {
            filtro = new TipoDocumento();
            filtro.setNome(getSessao("tdctrl_nome"));
            filtro.setSigla(getSessao("tdctrl_sigla"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(TipoDocumento filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("tdctrl_nome", filtro.getNome());
            setSessao("tdctrl_sigla",filtro.getSigla());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarTipoDocumento.xhtml");
        setPaginaListagem("listagemTipoDocumentos.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new TipoDocumento());
    }
}

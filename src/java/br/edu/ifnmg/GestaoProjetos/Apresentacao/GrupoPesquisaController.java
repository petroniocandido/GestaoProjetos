/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.GrupoPesquisa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.GrupoPesquisaRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "grupoPesquisaController")
@SessionScoped
public class GrupoPesquisaController
   extends ControllerBaseEntidade<GrupoPesquisa> implements Serializable {

    /**
     * Creates a new instance of GrupoPesquisaController
     */
    public GrupoPesquisaController() {
    }
    
    @EJB
    GrupoPesquisaRepositorio dao;
    
    @Override
    public GrupoPesquisa getFiltro() {
        if (filtro == null) {
            filtro = new GrupoPesquisa();
            filtro.setNome(getSessao("crctrl_nome"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(GrupoPesquisa filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("crctrl_nome", filtro.getNome());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarGrupoPesquisa.xhtml");
        setPaginaListagem("listagemGruposPesquisa.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new GrupoPesquisa());
    }
}

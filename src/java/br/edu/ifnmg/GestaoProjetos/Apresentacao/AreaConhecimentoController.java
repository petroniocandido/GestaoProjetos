/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "areaConhecimentoController")
@SessionScoped
public class AreaConhecimentoController
    extends ControllerBaseEntidade<AreaConhecimento> implements Serializable {

    /**
     * Creates a new instance of AreaConhecimentoController
     */
    public AreaConhecimentoController() {
    }
    
    @EJB
    AreaConhecimentoRepositorio dao;
    
    @Override
    public AreaConhecimento getFiltro() {
        if (filtro == null) {
            filtro = new AreaConhecimento();
            filtro.setNome(getSessao("acctrl_nome"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(AreaConhecimento filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("acctrl_nome", filtro.getNome());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarAreaConhecimento.xhtml");
        setPaginaListagem("listagemAreaConhecimento.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new AreaConhecimento());
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.DomainModel.Log;
import br.edu.ifnmg.DomainModel.Services.LogRepositorio;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaProjetoRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author petronio
 */
@Named(value = "logController")
@RequestScoped
public class LogController
        extends ControllerBaseEntidade<Log>
        implements Serializable {

    /**
     * Creates a new instance of FuncionarioBean
     */
    public LogController() {
    }

    @EJB
    LogRepositorio dao;

    @EJB
    PessoaProjetoRepositorio pessoaDAO;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarLog.xhtml");
        setPaginaListagem("listagemLogs.xtml");
    }

    @Override
    public Log getFiltro() {
        if (filtro == null) {
            filtro = new Log();
            filtro.setUsuario((PessoaProjeto) getSessao("logctrl_usuario", pessoaDAO));
            filtro.setDataEvento(getSessaoData("logctrl_data"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Log filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("logctrl_usuario", filtro.getUsuario());
            setSessao("logctrl_data", filtro.getDataEvento());
        }
    }

    @Override
    public void limpar() {
        setEntidade(new Log());
    }

    public void apagaFiltro() {
        dao.Apaga();
    }

    public void apagaTodos() {
        dao.Apaga();
    }

}

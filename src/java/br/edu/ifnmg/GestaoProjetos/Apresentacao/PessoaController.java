/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBasePessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaProjetoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author petronio
 */
@Named(value = "usuarioController")
@RequestScoped
public class PessoaController
        extends ControllerBasePessoa<PessoaProjeto>
        implements Serializable {

    /**
     * Creates a new instance of FuncionarioBean
     */
    public PessoaController() {
    }

    @EJB
    PessoaProjetoRepositorio dao;

    @EJB
    CampusRepositorio daoCampus;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarUsuario.xhtml");
        setPaginaListagem("listagemUsuarios.xtml");
    }

    @Override
    public PessoaProjeto getFiltro() {
        if (filtro == null) {
            filtro = new PessoaProjeto();
            filtro.setNome(getSessao("pctrl_nome"));
            filtro.setCpf(getSessao("pctrl_cpf"));
            filtro.setEmail(getSessao("pctrl_email"));
            filtro.setCampus((Campus) getSessao("pctrl_campus", daoCampus));
            if (filtro.getCampus() == null) {
                filtro.setCampus(getUsuarioCorrente().getCampus());
            }
        }
        return filtro;
    }

    @Override
    public void setFiltro(PessoaProjeto filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("pctrl_nome", filtro.getNome());
            setSessao("pctrl_cpf", filtro.getCpf());
            setSessao("pctrl_email", filtro.getEmail());
            setSessao("pctrl_campus", filtro.getCampus());
        }
    }

    @Override
    public void limpar() {
        setEntidade(new PessoaProjeto());
    }

    public List<PessoaProjeto> getPessoas() {
        if (getEntidade() != null) {
            List<PessoaProjeto> tmp = new ArrayList<>();
            tmp.add(entidade);
            return tmp;
        } else {
            return getListagem();
        }
    }

}

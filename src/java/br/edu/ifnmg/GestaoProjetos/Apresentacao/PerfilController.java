/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Perfil;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Permissao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PerfilRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author petronio
 */
@Named(value = "perfilController")
@RequestScoped
public class PerfilController
        extends ControllerBaseEntidade<Perfil>
        implements Serializable {

    /**
     * Creates a new instance of FuncionarioBean
     */
    public PerfilController() {
    }

    @EJB
    PerfilRepositorio dao;

    @EJB
    PessoaRepositorio daoP;

    Permissao permissao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarPerfil.xhtml");
        setPaginaListagem("listagemPerfis.xtml");
    }

    @Override
    public Perfil getFiltro() {
        if (filtro == null) {
            filtro = new Perfil();
            filtro.setNome(getSessao("pflctrl_nome"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Perfil filtro) {
        this.filtro = filtro;
        if(filtro != null)
            setSessao("pflctrl_nome", filtro.getNome());

    }

    @Override
    public void limpar() {
        setEntidade(new Perfil());
    }

    public void valueChangeListener(ValueChangeEvent evt) {
        setEntidade(dao.Refresh(getEntidade()));
        if ((boolean) evt.getNewValue()) {
            entidade.add(permissao);
        } else {
            entidade.remove(permissao);
        }
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public List<Pessoa> getPessoas() {
        return daoP.IgualA("perfil", entidade).Buscar();
    }

}

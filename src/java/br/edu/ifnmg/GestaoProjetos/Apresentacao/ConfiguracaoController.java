/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Configuracao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ConfiguracaoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author petronio
 */
@Named(value = "configuracaoController")
@RequestScoped
public class ConfiguracaoController
        extends ControllerBaseEntidade<Configuracao>
        implements Serializable {

    /**
     * Creates a new instance of FuncionarioBean
     */
    public ConfiguracaoController() {
    }

    @EJB
    ConfiguracaoRepositorio dao;

    @EJB
    PessoaRepositorio pessoaDAO;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarConfiguracao.xhtml");
        setPaginaListagem("listagemConfiguracoes.xhtml");
    }

    public List<Configuracao> autoCompleteConfiguracao(String query) {
        Configuracao i = new Configuracao();
        i.setChave(query);
        return dao.Buscar(i);
    }

    @Override
    public Configuracao getFiltro() {
        if (filtro == null) {
            filtro = new Configuracao();
            filtro.setChave(getSessao("cnfctrl_chave"));
            filtro.setUsuario((Pessoa) getSessao("cnfctrl_usuario", pessoaDAO));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Configuracao filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("cnfctrl_chave", filtro.getChave());
            setSessao("cnfctrl_usuario", filtro.getUsuario());
        }
    }

    @Override
    public void salvar() {

        Rastrear(getEntidade());

        // salva o objeto no BD
        if (dao.Set(entidade.getUsuario(), entidade.getChave(), entidade.getValor())) {

            setId(entidade.getId());

            Mensagem("Sucesso", "Registro salvo com sucesso!");
            AppendLog("Editou a entidade " + entidade.getClass().getSimpleName() + " " + entidade.getId() + "(" + entidade.toString() + ")");
        } else {
            MensagemErro("Falha", "Registro não foi salvo! Consulte o Log ou o administrador do sistema!");
            AppendLog("Falha ao editar a entidade " + entidade.getClass().getSimpleName() + " " + entidade.getId() + "(" + getEntidade().toString() + ")" + ": " + repositorio.getErro().getMessage());
        }

        // atualiza a listagem
        filtrar();
    }

    @Override
    public void limpar() {

        setEntidade(new Configuracao());
    }

}

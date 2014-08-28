/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Email;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Endereco;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Telefone;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "orientadorController")
@RequestScoped
public class OrientadorController
        extends ControllerBaseEntidade<Orientador> implements Serializable {

    Email email;
    Telefone telefone;
    Endereco endereco;
    AreaConhecimento areaConhecimento;

    /**
     * Creates a new instance of OrientadorController
     */
    public OrientadorController() {
        email = new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        areaConhecimento = new AreaConhecimento();
    }

    @EJB
    OrientadorRepositorio dao;

    @Override
    public Orientador getFiltro() {
        if (filtro == null) {
            filtro = new Orientador();
            filtro.setNome(getSessao("orctrl_nome"));
            filtro.setCpf(getSessao("orctrl_cpf"));
            filtro.setEmail(getSessao("orctrl_email"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Orientador filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("orctrl_nome", filtro.getNome());
            setSessao("orctrl_cpf",filtro.getCpf());
            setSessao("orctrl_email",filtro.getEmail());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarOrientador.xhtml");
        setPaginaListagem("listagemOrientadores.xhtml");
    }

    @Override
    public void limpar() {
        setEntidade(new Orientador());
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    //METODOS
    public void addTelefone() {
        //entidade = dao.Refresh(entidade.getId());
        entidade.addTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }

    public void addAreaConhecimento() {
        entidade.addAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
    }

    public void addEndereco() {
        entidade.addEndereco(endereco);
        dao.Salvar(entidade);
        endereco = new Endereco();
    }

    public void addEmail() {
        entidade.addEmail(email);
        dao.Salvar(entidade);
        email = new Email();
    }

    public void removeEndereco() {
        entidade.removeEndereco(endereco);
        dao.Salvar(entidade);
        endereco = new Endereco();
    }

    public void removeTelefone() {
        entidade.removeTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }

    public void removeEmail() {
        entidade.removeEmail(email);
        dao.Salvar(entidade);
        email = new Email();
    }

    public void removeAreaConhecimento() {
        entidade.removeAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
    }
}

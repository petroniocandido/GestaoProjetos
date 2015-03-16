/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBasePessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.UsuarioTipo;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
        extends ControllerBasePessoa<Orientador> implements Serializable {

    AreaConhecimento areaConhecimento;

    /**
     * Creates a new instance of OrientadorController
     */
    public OrientadorController() {
        areaConhecimento = new AreaConhecimento();
    }

    @EJB
    OrientadorRepositorio dao;
    
    @EJB
    CampusRepositorio daoCampus;

    @Override
    public Orientador getFiltro() {
        if (filtro == null) {
            filtro = new Orientador();
            filtro.setNome(getSessao("orctrl_nome"));
            filtro.setCpf(getSessao("orctrl_cpf"));
            filtro.setEmail(getSessao("orctrl_email"));
            filtro.setCampus((Campus)getSessao("orctrl_campus",daoCampus));
            if(filtro.getCampus() == null){
                setEntidade(dao.Abrir(getUsuarioCorrente().getId()));
            }
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
            setSessao("orctrl_campus",filtro.getCampus());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarOrientador.xhtml");
        setPaginaListagem("listagemOrientadores.xhtml");
        if(getUsuarioCorrente().getUsuarioTipo() == UsuarioTipo.Orientador){
            setEntidade(getOrientadorCorrente());
            setPaginaListagem("index.xhtml");
        }
    }

    @Override
    public void limpar() {
        setEntidade(new Orientador());
    }


    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public void removeAreaConhecimento() {
        entidade.removeAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
    }
    
     public List<Pessoa> getPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        for(Pessoa i : getListagem())
            pessoas.add(i);
        
        return pessoas;
    }
}

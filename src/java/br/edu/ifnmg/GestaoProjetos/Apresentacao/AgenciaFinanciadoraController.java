/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "agenciaFinanciadoraController")
@RequestScoped
public class AgenciaFinanciadoraController 
    extends ControllerBaseEntidade<AgenciaFinanciadora> implements Serializable {
 
    
    /**
     * Creates a new instance of AgenciaFinanciadoraController
     */
    
    public AgenciaFinanciadoraController() {
        filtro = new AgenciaFinanciadora();
        entidade = new AgenciaFinanciadora();       
    }

    @EJB
    AgenciaFinanciadoraRepositorio dao;

        @Override
    public AgenciaFinanciadora getFiltro() {
        if (filtro == null) {
            filtro = new AgenciaFinanciadora();
            filtro.setNome(getSessao("agctrl_nome"));
            filtro.setSigla(getSessao("agctrl_sigla"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(AgenciaFinanciadora filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("agctrl_nome", filtro.getNome());
            setSessao("agctrl_sigla", filtro.getSigla());
        }
    }
    
    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarAgenciaFinanciadora.xhtml");
        setPaginaListagem("listagemAgenciasFinanciadoras.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new AgenciaFinanciadora());
    }
  
}

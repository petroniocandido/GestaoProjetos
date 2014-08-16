/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "campusController")
@RequestScoped
public class CampusController
    extends ControllerBaseEntidade<Campus> implements Serializable {

    /**
     * Creates a new instance of CampusController
     */
    public CampusController() {        
        filtro = new Campus();
        entidade = new Campus();        
    }
    
    @EJB
    CampusRepositorio dao;
    
    @Override
    public Campus getFiltro() {
        if (filtro == null) {
            filtro = new Campus();
            filtro.setNome(getSessao("cctrl_nome"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Campus filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("cctrl_nome", filtro.getNome());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarCampus.xhtml");
        setPaginaListagem("listagemCampus.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Campus());
    }
}


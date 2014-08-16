/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Curso;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CursoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "cursoController")
@SessionScoped
public class CursoController
   extends ControllerBaseEntidade<Curso> implements Serializable {

    /**
     * Creates a new instance of CursoController
     */
    public CursoController() {
        filtro = new Curso();
        entidade = new Curso();
    }
    
    @EJB
    CursoRepositorio dao;
    
    @Override
    public Curso getFiltro() {
        if (filtro == null) {
            filtro = new Curso();
            filtro.setNome(getSessao("crctrl_nome"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Curso filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("crctrl_nome", filtro.getNome());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarCurso.xhtml");
        setPaginaListagem("listagemCurso.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Curso());
    }
}

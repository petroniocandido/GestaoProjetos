/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBasePessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
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
@Named(value = "alunoController")
@RequestScoped
public class AlunoController 
    extends ControllerBasePessoa<Aluno> implements Serializable {
    
    
    @EJB
    AlunoRepositorio dao;
    
    @EJB
    CampusRepositorio daoCampus;
    
    @Override
    public Aluno getFiltro() {
        if (filtro == null) {
            filtro = new Aluno();
            filtro.setNome(getSessao("alctrl_nome"));
            filtro.setCpf(getSessao("alctrl_cpf"));
            filtro.setEmail(getSessao("alctrl_email"));
            filtro.setCampus((Campus)getSessao("alctrl_campus",daoCampus));
            if(filtro.getCampus() == null){
                filtro.setCampus(getUsuarioCorrente().getCampus());
            }
        }
        return filtro;
    }

    @Override
    public void setFiltro(Aluno filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("alctrl_nome", filtro.getNome());
            setSessao("alctrl_cpf",filtro.getCpf());
            setSessao("alctrl_email",filtro.getEmail());
            setSessao("alctrl_campus",filtro.getCampus());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarAluno.xhtml");
        setPaginaListagem("listagemAlunos.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Aluno());
    }
    
}
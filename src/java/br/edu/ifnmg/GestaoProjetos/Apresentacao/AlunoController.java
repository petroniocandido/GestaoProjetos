/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Email;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Endereco;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
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
@Named(value = "alunoController")
@RequestScoped
public class AlunoController 
    extends ControllerBaseEntidade<Aluno> implements Serializable {
    
     Email email;
     Telefone telefone;
     Endereco endereco;

    /**
     * Creates a new instance of AlunoController
     */
    public AlunoController() {
        email= new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        
    }
    
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
    
    
    public void addTelefone(){
        //entidade = dao.Refresh(entidade.getId());
        entidade.addTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }
    
    public void addEndereco(){
        entidade.addEndereco(endereco);
        dao.Salvar(entidade);
        endereco = new Endereco();
    }
    
    public void addEmail(){
        entidade.addEmail(email);
        dao.Salvar(entidade);
        email = new Email();
    }
    
    public void removeEndereco(){
        entidade.removeEndereco(endereco);
        dao.Salvar(entidade);
        endereco = new Endereco();
    }
    
    public void removeTelefone(){
        entidade.removeTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }
     
   public void removeEmail(){
        entidade.removeEmail(email);
        dao.Salvar(entidade);
        email = new Email();
    } 
    

}
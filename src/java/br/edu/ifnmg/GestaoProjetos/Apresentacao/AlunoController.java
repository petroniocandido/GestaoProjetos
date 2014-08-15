/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Email;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Endereco;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Telefone;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "alunoController")
@SessionScoped
public class AlunoController 
    extends ControllerGenerico<Aluno> implements Serializable {
    
     Email email;
     Telefone telefone;
     Endereco endereco;

    /**
     * Creates a new instance of AlunoController
     */
    public AlunoController() {
        
        filtro = new Aluno();
        entidade = new Aluno();
        email= new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        
    }
    
    @EJB
    AlunoRepositorio dao;
     
    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            listagem = null;            
            
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new Aluno();
        return "editarAluno.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarAluno.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAluno.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemAluno.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    
    
   //getter e setter
    
    public AlunoRepositorio getDao() {
        return dao;
    }

    public void setDao(AlunoRepositorio dao) {
        this.dao = dao;
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
    
    
    
    //Metodos
    
    
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Email;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Endereco;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Orientador;
import br.edu.ifnmg.gestaoprojetos.DomainModel.OrientadorRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Telefone;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "orientadorController")
@SessionScoped
public class OrientadorController
    extends ControllerGenerico<Orientador> implements Serializable {
    
    
     Email email;
     Telefone telefone;
     Endereco endereco;
     AreaConhecimento areaConhecimento;

    /**
     * Creates a new instance of OrientadorController
     */
    public OrientadorController() {
        filtro = new Orientador();
        entidade = new Orientador();
        email= new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        areaConhecimento = new AreaConhecimento();
    }
    
    @EJB
    OrientadorRepositorio dao;
    
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
        entidade = new Orientador();
        return "editarOrientador.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarOrientador.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemOrientador.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null; 
            return "listagemOrientador.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }
    
    //GETTER E SETTER

    public OrientadorRepositorio getDao() {
        return dao;
    }

    public void setDao(OrientadorRepositorio dao) {
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

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }
    
    
    
    //METODOS
    
    
    public void addTelefone(){
        //entidade = dao.Refresh(entidade.getId());
        entidade.addTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }
    
    public void addAreaConhecimento(){
        entidade.addAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
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
   
    public void removeAreaConhecimento(){
        entidade.removeAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
    } 
    
    
    
    

}


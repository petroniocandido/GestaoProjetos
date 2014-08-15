/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "projetoController")
@SessionScoped
public class ProjetoController 
    extends ControllerGenerico<Projeto> implements Serializable {
    
    AreaConhecimento areaConhecimento;
    Aluno orientando;
    Documento documento;
    Atividade atividade;

    /**
     * Creates a new instance of ProjetoController
     */
    
    public ProjetoController() {      
        filtro = new Projeto();
        entidade = new Projeto();  
        areaConhecimento = new AreaConhecimento();
        //orientandos = new Aluno();
        documento = new Documento();
        atividade = new Atividade();
    }
    
    @EJB
    ProjetoRepositorio dao;
    
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
        entidade = new Projeto();
        return "editarProjeto.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarProjeto.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemProjeto.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null; 
            return "listagemProjeto.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public ProjetoRepositorio getDao() {
        return dao;
    }

    public void setDao(ProjetoRepositorio dao) {
        this.dao = dao;
    }

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public Aluno getOrientando() {
        return orientando;
    }

    public void setOrientando(Aluno orientandos) {
        this.orientando = orientandos;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documentos) {
        this.documento = documentos;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    
    
    //Metodos
    
      public void addAreaConhecimento(){
        entidade.addAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
     }
      
      public void removeAreaConhecimento(){
        entidade.removeAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
      } 
      
       public void addAluno(){
        entidade.addAluno(orientando);
        dao.Salvar(entidade);
        //orientandos = new Aluno();
     }
      
      public void removeAluno(){
        entidade.removeAluno(orientando);
        dao.Salvar(entidade);
        //orientandos = new Aluno();
      } 
      
      public void addDocumento(){
        entidade.addDocumento(documento);
        dao.Salvar(entidade);
        documento = new Documento();
     }
      
      public void removeDocumento(){
        entidade.removeDocumento(documento);
        dao.Salvar(entidade);
        documento = new Documento();
      } 
      
      public void addAtividade(){
        entidade.addAtividade(atividade);
        dao.Salvar(entidade);
        atividade = new Atividade();
     }
      
      public void removeAtividade(){
        entidade.removeAtividade(atividade);
        dao.Salvar(entidade);
        atividade = new Atividade();
      } 
    
}


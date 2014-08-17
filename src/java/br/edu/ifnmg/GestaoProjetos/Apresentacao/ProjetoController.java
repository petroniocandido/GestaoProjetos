/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "projetoController")
@SessionScoped
public class ProjetoController 
    extends ControllerBaseEntidade<Projeto> implements Serializable {
    
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
    
    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;
    
    @EJB
    CampusRepositorio daoCampus;
    
    @EJB
    EditalRepositorio daoEdital;
    
     @Override
    public Projeto getFiltro() {
        if (filtro == null) {
            filtro = new Projeto();
            filtro.setTitulo(getSessao("prctrl_titulo"));
            filtro.setAgenciaFinanciadora((AgenciaFinanciadora)getSessao("prctrl_agencia",daoAgencia));
            filtro.setCampus((Campus)getSessao("prctrl_campus",daoCampus));
            filtro.setEdital((Edital)getSessao("prctrl_edital",daoEdital));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Projeto filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("prctrl_titulo",filtro.getTitulo());
            setSessao("prctrl_agencia",filtro.getAgenciaFinanciadora());
            setSessao("prctrl_campus",filtro.getCampus());
            setSessao("prctrl_edital",filtro.getEdital());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarProjeto.xhtml");
        setPaginaListagem("listagemProjetos.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Projeto());
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


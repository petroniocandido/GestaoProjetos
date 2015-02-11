/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AtividadeAcompanhamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AtividadeSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Financiamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.ProjetoSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.ProjetoTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.UsuarioTipo;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "projetoController")
@RequestScoped
public class ProjetoController
        extends ControllerBaseEntidade<Projeto> implements Serializable {

    AreaConhecimento areaConhecimento;
    Bolsa bolsa;
    Documento documento;
    Atividade atividade;
    AtividadeAcompanhamento acompanhamento;
    Financiamento financiamento;
    ProjetoSituacao[] situacoesProjeto;
    AtividadeSituacao[] situacoesAtividade;

    /**
     * Creates a new instance of ProjetoController
     */
    public ProjetoController() {
        areaConhecimento = new AreaConhecimento();
        documento = new Documento();
        atividade = new Atividade();
        documento = new Documento();
        acompanhamento = new AtividadeAcompanhamento();
        financiamento = new Financiamento();
    }
    
    
    Boolean alteraDadosGerais;
    public boolean isAlteraDadosGerais() {
        if(alteraDadosGerais == null){
            alteraDadosGerais = isAdmin() || (getUsuarioCorrente().getUsuarioTipo() == UsuarioTipo.Orientador 
                    && getEntidade().isAlteravel());
        }
        return alteraDadosGerais;
    }
    

    @EJB
    ProjetoRepositorio dao;

    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;

    @EJB
    CampusRepositorio daoCampus;

    @EJB
    EditalRepositorio daoEdital;

    @EJB
    ModalidadeRepositorio daoModalidade;

    @Override
    public Projeto getFiltro() {
        if (filtro == null) {
            filtro = new Projeto();
            filtro.setTitulo(getSessao("prctrl_titulo"));
            filtro.setCampus((Campus) getSessao("prctrl_campus", daoCampus));
            if (filtro.getCampus() == null) {
                filtro.setCampus(getUsuarioCorrente().getCampus());
            }
        }
        return filtro;
    }

    @Override
    public void setFiltro(Projeto filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("prctrl_titulo", filtro.getTitulo());
            setSessao("prctrl_campus", filtro.getCampus());
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
    
    @Override
    public List<Projeto> getListagem() {
        switch(getUsuarioCorrente().getUsuarioTipo()) {
            case Pessoa:
                return dao.Buscar(getFiltro());
             
           case Aluno:
                return dao.doOrientando(getAlunoCorrente());
               
           case Orientador:
                return dao.doOrientador(getOrientadorCorrente());
        }
        
        return null;
    }

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa b) {
        this.bolsa = b;
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
    public void addAreaConhecimento() {
        getEntidade().addAreaConhecimento(areaConhecimento);
        SalvarAgregado(areaConhecimento);
        areaConhecimento = new AreaConhecimento();
    }

    public void removeAreaConhecimento() {
        getEntidade().removeAreaConhecimento(areaConhecimento);
        RemoverAgregado(areaConhecimento);
        areaConhecimento = new AreaConhecimento();
    }

    public void addBolsa() {
        getEntidade().addBolsa(bolsa);
        SalvarAgregado(bolsa);
        //orientandos = new Aluno();
    }

    public void removeBolsa() {
        getEntidade().removeBolsa(bolsa);
        RemoverAgregado(bolsa);
        //orientandos = new Aluno();
    }

    public void addDocumento() {
        getEntidade().addDocumento(documento);
        SalvarAgregado(documento);
        documento = new Documento();
    }

    public void removeDocumento() {
        getEntidade().removeDocumento(documento);
        RemoverAgregado(documento);
        documento = new Documento();
    }


    public void addFinanciamento() {
        getEntidade().addFinanciamento(financiamento);
        SalvarAgregado(financiamento);
        financiamento = new Financiamento();
    }

    public void removeFinanciamento() {
        getEntidade().removeFinanciamento(financiamento);
        RemoverAgregado(financiamento);
        financiamento = new Financiamento();
    }
    
    
    ProjetoTipo[] tipos;
    public ProjetoTipo[] getTiposProjeto() {
        if (tipos == null) {
            tipos = ProjetoTipo.values();
        }
        return tipos;
    }
    
    public ProjetoSituacao[] getSituacoesProjeto() {
        if (situacoesProjeto == null) {
            situacoesProjeto = ProjetoSituacao.values();
        }
        return situacoesProjeto;
    }

    public AtividadeSituacao[] getSituacoesAtividade() {
        if (situacoesAtividade == null) {
            situacoesAtividade = AtividadeSituacao.values();
        }
        return situacoesAtividade;
    }

    public Financiamento getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(Financiamento orcamento) {
        this.financiamento = orcamento;
    }


    public AtividadeAcompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(AtividadeAcompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}

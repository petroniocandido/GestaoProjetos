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
import br.edu.ifnmg.GestaoProjetos.DomainModel.AtividadeAcompanhamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AtividadeSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orcamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.OrcamentoExecucao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.ProjetoSituacao;
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
    Aluno orientando;
    Documento documento;
    Atividade atividade;
    AtividadeAcompanhamento acompanhamento;
    Orcamento orcamento;
    OrcamentoExecucao execucao;
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
        orcamento = new Orcamento();
        execucao = new OrcamentoExecucao();
    }
    
    Boolean admin;
    public boolean isAdmin() {
        if(admin == null){
            admin = getUsuarioCorrente().getUsuarioTipo() == UsuarioTipo.Pessoa;
        }
        return admin;
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
            filtro.setAgenciaFinanciadora((AgenciaFinanciadora) getSessao("prctrl_agencia", daoAgencia));
            filtro.setCampus((Campus) getSessao("prctrl_campus", daoCampus));
            filtro.setEdital((Edital) getSessao("prctrl_edital", daoEdital));
            filtro.setModalidade((Modalidade) getSessao("prctrl_modalidade", daoModalidade));
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
            setSessao("prctrl_agencia", filtro.getAgenciaFinanciadora());
            setSessao("prctrl_campus", filtro.getCampus());
            setSessao("prctrl_edital", filtro.getEdital());
            setSessao("prctrl_modalidade", filtro.getModalidade());
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

    public void addAluno() {
        getEntidade().addAluno(orientando);
        SalvarAgregado(orientando);
        //orientandos = new Aluno();
    }

    public void removeAluno() {
        getEntidade().removeAluno(orientando);
        RemoverAgregado(orientando);
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

    public void addAtividade() {
        getEntidade().addAtividade(atividade);
        SalvarAgregado(atividade);        
        atividade = new Atividade();
    }

    public void removeAtividade() {
        getEntidade().removeAtividade(atividade);
        RemoverAgregado(atividade);
        atividade = new Atividade();
    }

    public void addOrcamento() {
        getEntidade().addOrcamento(orcamento);
        SalvarAgregado(orcamento);
        orcamento = new Orcamento();
    }

    public void removeOrcamento() {
        getEntidade().removeOrcamento(orcamento);
        RemoverAgregado(orcamento);
        orcamento = new Orcamento();
    }

    public void addExecucao() {
        orcamento.add(execucao);
        SalvarAgregado(execucao);
        execucao = new OrcamentoExecucao();
    }

    public void removeExecucao() {
        orcamento.remove(execucao);
        RemoverAgregado(execucao);
        execucao = new OrcamentoExecucao();
    }
    
    public void addAcompanhamento() {
        acompanhamento.setPessoa(getUsuarioCorrente());
        getEntidade().addAtividadeAcompanhamento(acompanhamento);
        SalvarAgregado(acompanhamento);
        acompanhamento = new AtividadeAcompanhamento();
    }

    public void removeAcompanhamento() {
        getEntidade().removeAtividadeAcompanhamento(acompanhamento);
        RemoverAgregado(acompanhamento);
        acompanhamento = new AtividadeAcompanhamento();
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

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public OrcamentoExecucao getExecucao() {
        return execucao;
    }

    public void setExecucao(OrcamentoExecucao execucao) {
        this.execucao = execucao;
    }

    public AtividadeAcompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(AtividadeAcompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}

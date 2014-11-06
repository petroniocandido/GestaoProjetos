/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AtividadeAcompanhamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AtividadeSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.ProjetoSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.BolsaRepositorio;
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
@Named(value = "bolsaController")
@RequestScoped
public class BolsaController
        extends ControllerBaseEntidade<Bolsa> implements Serializable {

    Documento documento;
    Atividade atividade;
    AtividadeAcompanhamento acompanhamento;
    ProjetoSituacao[] situacoesProjeto;
    AtividadeSituacao[] situacoesAtividade;

    /**
     * Creates a new instance of ProjetoController
     */
    public BolsaController() {
        documento = new Documento();
        atividade = new Atividade();
        documento = new Documento();
        acompanhamento = new AtividadeAcompanhamento();
    }

    Boolean admin;

    public boolean isAdmin() {
        if (admin == null) {
            admin = getUsuarioCorrente().getUsuarioTipo() == UsuarioTipo.Pessoa;
        }
        return admin;
    }

    Boolean alteraDadosGerais;

    public boolean isAlteraDadosGerais() {
        if (alteraDadosGerais == null) {
            alteraDadosGerais = isAdmin() || (getUsuarioCorrente().getUsuarioTipo() == UsuarioTipo.Orientador);
        }
        return alteraDadosGerais;
    }

    @EJB
    BolsaRepositorio dao;

    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;

    @EJB
    EditalRepositorio daoEdital;

    @EJB
    ModalidadeRepositorio daoModalidade;

    @Override
    public Bolsa getFiltro() {
        if (filtro == null) {
            filtro = new Bolsa();
            filtro.setAgenciaFinanciadora((AgenciaFinanciadora) getSessao("prctrl_agencia", daoAgencia));
            filtro.setEdital((Edital) getSessao("prctrl_edital", daoEdital));
            filtro.setModalidade((Modalidade) getSessao("prctrl_modalidade", daoModalidade));

        }
        return filtro;
    }

    @Override
    public void setFiltro(Bolsa filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("prctrl_agencia", filtro.getAgenciaFinanciadora());
            setSessao("prctrl_edital", filtro.getEdital());
            setSessao("prctrl_modalidade", filtro.getModalidade());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarBolsa.xhtml");
        setPaginaListagem("listagemBolsas.xhtml");
    }

    @Override
    public void limpar() {
        setEntidade(new Bolsa());
    }

    @Override
    public List<Bolsa> getListagem() {
        switch (getUsuarioCorrente().getUsuarioTipo()) {
            case Pessoa:
                return dao.Buscar(getFiltro());

            case Aluno:
                return dao.doOrientando(getAlunoCorrente());

            case Orientador:
                return dao.doOrientador(getOrientadorCorrente());

        }

        return null;
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

    public AtividadeAcompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(AtividadeAcompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}

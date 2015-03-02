/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBase;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.BolsaRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.DocumentoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.HashService;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "cadastroRapidoProjetoController")
@RequestScoped
public class CadastroRapidoProjetoController
        extends ControllerBase implements Serializable {

    /**
     * Creates a new instance of AgenciaFinanciadoraController
     */
    public CadastroRapidoProjetoController() {
        documento = new Documento();
        areaConhecimento = new AreaConhecimento();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.MONTH, 12);
        ultimoDia = cal.getTime();
    }

    Calendar cal = new GregorianCalendar();

    Date ultimoDia;

    @EJB
    ProjetoRepositorio daoProjeto;

    @EJB
    OrientadorRepositorio daoOrientador;

    @EJB
    AlunoRepositorio daoAluno;

    @EJB
    BolsaRepositorio daoBolsa;

    @EJB
    DocumentoRepositorio daoDocumento;

    @Inject
    HashService hash;

    Projeto projeto;

    Orientador orientador;

    Aluno aluno;

    Bolsa bolsa;

    Documento documento;

    AreaConhecimento areaConhecimento;

    public void salvar() {
        Rastrear(projeto);
        if (daoProjeto.Salvar(projeto)) {
            Mensagem("Sucesso", "Registro salvo com sucesso!");
            AppendLog("Editou a entidade " + projeto.getClass().getSimpleName() + " " + projeto.getId() + "(" + projeto.toString() + ")");
            setProjeto(projeto);
        } else {
            MensagemErro("Falha", "Registro não foi salvo! Consulte o Log ou o administrador do sistema!");
            AppendLog("Falha ao editar a entidade " + projeto.getClass().getSimpleName() + " " + projeto.getId() + "(" + projeto.toString() + ")" + ": " + daoProjeto.getErro().getMessage());
        }
    }

    public String novo() {
        setProjeto(null);
        setOrientador(null);
        setAluno(null);
        setBolsa(null);

        return "cadastroRapidoProjeto.xhtml";
    }

    //Metodos
    public void addAreaConhecimento() {
        getProjeto().addAreaConhecimento(areaConhecimento);
        daoProjeto.Salvar(projeto);
        areaConhecimento = new AreaConhecimento();
    }

    public void removeAreaConhecimento() {
        getProjeto().removeAreaConhecimento(areaConhecimento);
        daoProjeto.Salvar(projeto);
        areaConhecimento = new AreaConhecimento();
    }

    public Projeto getProjeto() {
        if (projeto == null) {
            projeto = (Projeto) getSessao("projeto", daoProjeto);
        }
        if (projeto == null) {
            projeto = new Projeto();
            projeto.setDataInicio(new Date());
            projeto.setDataTermino(ultimoDia);
        }
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
        setSessao("projeto", projeto);
    }

    public Orientador getOrientador() {
        if (orientador == null) {
            orientador = (Orientador) getSessao("orientador", daoOrientador);
        }
        if (orientador == null) {
            orientador = new Orientador();
        }
        return orientador;
    }

    public void salvarOrientador() {
        Rastrear(orientador);
        orientador.setSenha(hash.getMD5("123"));
        orientador.setCampus(getProjeto().getCampus());
        if (daoOrientador.Salvar(orientador)) {
            setOrientador(orientador);
            getProjeto().setCoordenador(orientador);
            salvar();
        }
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
        setSessao("orientador", orientador);
    }

    public Aluno getAluno() {
        if (aluno == null) {
            aluno = (Aluno) getSessao("aluno", daoAluno);
        }
        if (aluno == null) {
            aluno = new Aluno();
        }
        return aluno;
    }

    public void salvarAluno() {
        Rastrear(aluno);
        aluno.setSenha(hash.getMD5("123"));
        aluno.setCampus(getProjeto().getCampus());
        if (daoAluno.Salvar(aluno)) {
            setAluno(aluno);
            Mensagem("Sucesso", "Aluno salvo com sucesso!");
        } else {
            MensagemErro("Falha", "Registro não foi salvo! Consulte o Log ou o administrador do sistema!");
            AppendLog("Falha ao editar a entidade: " + daoAluno.getErro().getMessage());
        }
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        setSessao("aluno", aluno);
    }

    public Bolsa getBolsa() {
        if (bolsa == null) {
            bolsa = (Bolsa) getSessao("bolsa", daoBolsa);
        }
        if (bolsa == null) {
            bolsa = new Bolsa();
        }
        return bolsa;
    }

    public void salvarBolsa() {
        bolsa.setOrientando(getAluno());
        bolsa.setProjeto(getProjeto());
        bolsa.setDataInicio(projeto.getDataInicio());
        bolsa.setDataTermino(projeto.getDataTermino());
        Rastrear(bolsa);
        if (daoBolsa.Salvar(bolsa)) {
            setBolsa(bolsa);
            Mensagem("Sucesso", "Bolsa salvo com sucesso!");
        } else {
            MensagemErro("Falha", "Registro não foi salvo! Consulte o Log ou o administrador do sistema!");
            AppendLog("Falha ao editar a entidade: " + daoBolsa.getErro().getMessage());
        }
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
        setSessao("bolsa", bolsa);
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

}

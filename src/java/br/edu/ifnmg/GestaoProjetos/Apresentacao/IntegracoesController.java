/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBase;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange.AreaConhecimentoCSVImporter;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange.OrientadorCSVImporter;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange.OrientandoCSVImporter;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange.PessoaCSVImporter;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange.ProjetoCSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.HashService;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author petronio
 */
@Named(value = "integracoesController")
@RequestScoped
public class IntegracoesController
        extends ControllerBase
        implements Serializable {

    /**
     * Creates a new instance of FuncionarioBean
     */
    public IntegracoesController() {

    }
    @EJB
    PessoaRepositorio daoP;

    @EJB
    AreaConhecimentoRepositorio daoArea;

    @EJB
    ProjetoRepositorio daoProjeto;

    @EJB
    OrientadorRepositorio daoOrientador;

    @EJB
    AlunoRepositorio daoOrientando;
    
    @Inject
    HashService hash;

    UploadedFile arquivo;

    String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }

    public void importar() {
        switch (tipo) {
            case "area":
                importarAreasConhecimento();
                break;
            case "Orientadores":
                importarOrientadores();
                break;
            case "Orientandos":
                importarOrientando();
                break;
            case "Pessoas":
                importarPessoas();
                break;
            case "Projetos":
                importarProjetos();
                break;
        }
    }

    public String abreArquivo() {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(arquivo.getInputstream()));
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void importarPessoas() {
        CSVImporter imp = new PessoaCSVImporter();
        List<Pessoa> tmp = imp.importarCSV(abreArquivo());
        for (Pessoa p : tmp) {
            if (daoP.AbrirPorCPF(p.getCpf()) == null) {
                p.setSenha(hash.getMD5("123456"));
                Rastrear(p);
                if (daoP.Salvar(p)) {
                    Mensagem(p.getNome() + "Salvo com sucesso", p.getNome() + "Salvo com sucesso");
                } else {
                    Mensagem(p.getNome() + " - Falhou", p.getNome() + " - Falhou");
                }
            } else {
                Mensagem(p.getNome() + "Já importado", p.getNome() + "Já importado");
            }
        }
    }

    public void importarAreasConhecimento() {
        CSVImporter imp = new AreaConhecimentoCSVImporter();
        List<AreaConhecimento> tmp = imp.importarCSV(abreArquivo());
        for (AreaConhecimento p : tmp) {
            if (daoArea.Abrir(p.getNumeroCNPQ()) == null) {
                if (daoArea.Salvar(p)) {
                    Mensagem(p.getNome() + "Salvo com sucesso", p.getNome() + "Salvo com sucesso");
                } else {
                    Mensagem(p.getNome() + " - Falhou", p.getNome() + " - Falhou");
                }
            } else {
                Mensagem(p.getNome() + "Já importado", p.getNome() + "Já importado");
            }

        }

    }

    public void importarProjetos() {
        CSVImporter imp = new ProjetoCSVImporter();
        List<Projeto> tmp = imp.importarCSV(abreArquivo());
        for (Projeto p : tmp) {
            Rastrear(p);
            if (daoProjeto.Salvar(p)) {
                Mensagem(p.getTitulo() + "Salvo com sucesso", p.getTitulo() + "Salvo com sucesso");
            } else {
                Mensagem(p.getTitulo() + " - Falhou", p.getTitulo() + " - Falhou");
            }
        }
    }

    public void importarOrientadores() {
        CSVImporter imp = new OrientadorCSVImporter();
        List<Orientador> tmp = imp.importarCSV(abreArquivo());
        for (Orientador p : tmp) {
            if (daoOrientador.AbrirPorCPF(p.getCpf()) == null) {
                p.setSenha(hash.getMD5("123456"));
                Rastrear(p);
                if (daoOrientador.Salvar(p)) {
                    Mensagem(p.getNome() + "Salvo com sucesso", p.getNome() + "Salvo com sucesso");
                } else {
                    Mensagem(p.getNome() + " - Falhou", p.getNome() + " - Falhou");
                }
            } else {
                Mensagem(p.getNome() + "Já importado", p.getNome() + "Já importado");
            }
        }
    }

    public void importarOrientando() {
        CSVImporter imp = new OrientandoCSVImporter();
        List<Aluno> tmp = imp.importarCSV(abreArquivo());
        for (Aluno p : tmp) {
            if (daoOrientando.AbrirPorCPF(p.getCpf()) == null) {
                p.setSenha(hash.getMD5("123456"));
                Rastrear(p);
                if (daoOrientando.Salvar(p)) {
                    Mensagem(p.getNome() + "Salvo com sucesso", p.getNome() + "Salvo com sucesso");
                } else {
                    Mensagem(p.getNome() + " - Falhou", daoOrientando.getErro().getMessage());
                }
            } else {
                Mensagem(p.getNome() + "Já importado", p.getNome() + "Já importado");
            }
        }
    }
}

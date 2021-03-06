/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaProjetoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author petronio
 */
@Named(value = "manutencaoController")
@RequestScoped
public class ManutencaoController
        extends ControllerBaseEntidade<Projeto>
        implements Serializable {

    
    
    @EJB
    PessoaProjetoRepositorio pessoaDAO;
    
    /**
     * Creates a new instance of FuncionarioBean
     */
    public ManutencaoController() {

    }

    @PostConstruct
    public void init() {
    }

    @Override
    public void limpar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    String texto;
    
    List<PessoaProjeto> pessoas;
    
  
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<PessoaProjeto> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaProjeto> pessoas) {
        this.pessoas = pessoas;
    }


    

}

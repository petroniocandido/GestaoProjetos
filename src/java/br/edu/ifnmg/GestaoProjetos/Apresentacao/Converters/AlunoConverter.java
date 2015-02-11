/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.Infraestrutura.AutenticacaoService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author HOME
 */
@Named(value = "alunoConverter")
@Singleton
public class AlunoConverter
        extends GenericConverter<Aluno, AlunoRepositorio>
        implements Serializable {

    @EJB
    AlunoRepositorio dao;
    
    @Inject
    protected AutenticacaoService autenticacao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Aluno> autoCompleteAluno(String query) {
        Aluno i = new Aluno();
        i.setCampus(autenticacao.getUsuarioCorrente().getCampus());
        i.setNome(query);
        return dao.Buscar(i);
    }
}

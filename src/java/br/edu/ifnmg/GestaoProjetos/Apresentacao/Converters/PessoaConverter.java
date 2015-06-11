/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;


import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaProjetoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author petronio
 */
@Named(value = "usuarioConverter")
@Singleton
public class PessoaConverter
        extends GenericConverter<PessoaProjeto, PessoaProjetoRepositorio>
        implements Serializable {

    @EJB
    PessoaProjetoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<PessoaProjeto> autoCompletePessoa(String query) {
        PessoaProjeto i = new PessoaProjeto();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

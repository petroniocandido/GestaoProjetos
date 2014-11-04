/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orcamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrcamentoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Singleton;

/**
 *
 * @author HOME
 */
@Named(value = "orcamentoConverter")
@Singleton
public class OrcamentoConverter
        extends GenericConverter<Orcamento, OrcamentoRepositorio>
        implements Serializable {

    @EJB
    OrcamentoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Orcamento> autoCompleteEdital(String query) {
        Orcamento i = new Orcamento();
        i.setDescricao(query);
        return dao.Buscar(i);
    }
}

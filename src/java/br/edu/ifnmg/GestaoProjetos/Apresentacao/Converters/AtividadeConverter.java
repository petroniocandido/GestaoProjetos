/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AtividadeRepositorio;
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
@Named(value = "atividadeConverter")
@Singleton
public class AtividadeConverter
        extends GenericConverter<Atividade, AtividadeRepositorio>
        implements Serializable {

    @EJB
    AtividadeRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Atividade> autoCompleteEdital(String query) {
        Atividade i = new Atividade();
        i.setDescricao(query);
        return dao.Buscar(i);
    }
}

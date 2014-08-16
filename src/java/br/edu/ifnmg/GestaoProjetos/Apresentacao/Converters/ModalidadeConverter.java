/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
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
@Named(value = "modalidadeConverter")
@Singleton
public class ModalidadeConverter
        extends GenericConverter<Modalidade, ModalidadeRepositorio>
        implements Serializable {

    @EJB
    ModalidadeRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Modalidade> autoCompleteModalidade(String query) {
        Modalidade i = new Modalidade();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

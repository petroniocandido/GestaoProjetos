/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
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
@Named(value = "editalConverter")
@Singleton
public class EditalConverter
        extends GenericConverter<Edital, EditalRepositorio>
        implements Serializable {

    @EJB
    EditalRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Edital> autoCompleteEdital(String query) {
        Edital i = new Edital();
        i.setSigla(query);
        return dao.Buscar(i);
    }
}

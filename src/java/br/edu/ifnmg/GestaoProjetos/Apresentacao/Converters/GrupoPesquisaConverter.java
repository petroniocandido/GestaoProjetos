/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.GrupoPesquisa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.GrupoPesquisaRepositorio;
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
@Named(value = "grupoPesquisaConverter")
@Singleton
public class GrupoPesquisaConverter
        extends GenericConverter<GrupoPesquisa, GrupoPesquisaRepositorio>
        implements Serializable {

    @EJB
    GrupoPesquisaRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<GrupoPesquisa> autoCompleteGrupoPesquisa(String query) {
        GrupoPesquisa i = new GrupoPesquisa();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

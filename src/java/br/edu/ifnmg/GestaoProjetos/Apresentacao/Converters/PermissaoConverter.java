/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.DomainModel.Permissao;
import br.edu.ifnmg.DomainModel.Services.PermissaoRepositorio;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
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
@Named(value = "permissaoConverter")
@Singleton
public class PermissaoConverter
        extends GenericConverter<Permissao, PermissaoRepositorio>
        implements Serializable {

    @EJB
    PermissaoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
     public List<Permissao> autoCompletePermissao(String query) {
        Permissao i = new Permissao();
        i.setUri(query);
        return dao.Buscar(i);
    }
}

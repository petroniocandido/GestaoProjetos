/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.DomainModel.Perfil;
import br.edu.ifnmg.DomainModel.Services.PerfilRepositorio;
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
@Named(value = "perfilConverter")
@Singleton
public class PerfilConverter
        extends GenericConverter<Perfil, PerfilRepositorio>
        implements Serializable {

    @EJB
    PerfilRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
     public List<Perfil> autoCompletePerfil(String query) {
        Perfil i = new Perfil();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

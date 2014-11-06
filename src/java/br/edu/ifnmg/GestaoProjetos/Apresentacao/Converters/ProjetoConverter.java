/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
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
@Named(value = "projetoConverter")
@Singleton
public class ProjetoConverter
        extends GenericConverter<Projeto, ProjetoRepositorio>
        implements Serializable {

    @EJB
    ProjetoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Projeto> autoCompleteProjeto(String query) {
        Projeto i = new Projeto();
        i.setTitulo(query);
        return dao.Buscar(i);
    }
}

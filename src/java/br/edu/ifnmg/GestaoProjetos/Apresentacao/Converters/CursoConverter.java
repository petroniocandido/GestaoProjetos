/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Curso;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CursoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "cursoConverter")
@Singleton
public class CursoConverter
        extends GenericConverter<Curso, CursoRepositorio>
        implements Serializable {

    @EJB
    CursoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Curso> autoCompleteCurso(String query) {
        Curso i = new Curso();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

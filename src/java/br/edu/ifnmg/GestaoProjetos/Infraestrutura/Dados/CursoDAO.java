/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Curso;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CursoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class CursoDAO
        extends DAOGenerico<Curso>
        implements CursoRepositorio {

    public CursoDAO() {
        super(Curso.class);
    }

    @Override
    public List<Curso> Buscar(Curso filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("sigla", filtro.getSigla())
                .Ordenar("nome", "ASC")
                .Buscar();
    }

}

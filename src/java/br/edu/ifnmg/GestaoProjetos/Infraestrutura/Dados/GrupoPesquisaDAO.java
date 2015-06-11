/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.GrupoPesquisa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.GrupoPesquisaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class GrupoPesquisaDAO
        extends DAO<GrupoPesquisa>
        implements GrupoPesquisaRepositorio {

    public GrupoPesquisaDAO() {
        super(GrupoPesquisa.class);
    }

    @Override
    public List<GrupoPesquisa> Buscar(GrupoPesquisa filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("sigla", filtro.getSigla())
                .Ordenar("nome", "DESC")
                .Buscar();
    }


}

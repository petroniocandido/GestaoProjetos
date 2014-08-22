/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name = "CampusRepositorio")
public class CampusDAO
        extends DAOGenerico<Campus>
        implements CampusRepositorio {

    public CampusDAO() {
        super(Campus.class);
    }

    @Override
    public List<Campus> Buscar(Campus filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .Ordenar("nome", "ASC")
                .Buscar();
    }

    @Override
    public Campus Abrir(String sigla) {
        return IgualA("sigla", sigla).Abrir();
    }

}

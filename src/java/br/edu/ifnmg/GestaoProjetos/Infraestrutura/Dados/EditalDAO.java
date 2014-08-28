/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name = "EditalRepositorio")
public class EditalDAO
        extends DAOGenerico<Edital>
        implements EditalRepositorio {

    public EditalDAO() {
        super(Edital.class);
    }

    @Override
    public List<Edital> Buscar(Edital filtro) {
        return IgualA("id", filtro.getId())
                .IgualA("numero", filtro.getNumero())
                .IgualA("agenciafinanciadora", filtro.getAgenciaFinanciadora())
                .Buscar();
    }

    @Override
    public Edital Abrir(String sigla) {
        return IgualA("sigla", sigla).Abrir();
    }

}

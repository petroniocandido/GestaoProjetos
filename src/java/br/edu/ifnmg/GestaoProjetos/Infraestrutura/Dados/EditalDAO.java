/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class EditalDAO
        extends DAO<Edital>
        implements EditalRepositorio {

    public EditalDAO() {
        super(Edital.class);
    }

    @Override
    public List<Edital> Buscar(Edital filtro) {
        return IgualA("id", filtro.getId())
                .IgualA("numero", filtro.getNumero())
                .IgualA("campus", filtro.getCampus())
                .Buscar();
    }

    @Override
    public Edital Abrir(String sigla) {
        return IgualA("sigla", sigla).Abrir();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class AreaConhecimentoDAO
        extends DAO<AreaConhecimento>
        implements AreaConhecimentoRepositorio {

    public AreaConhecimentoDAO() {
        super(AreaConhecimento.class);
    }

    @Override
    public List<AreaConhecimento> Buscar(AreaConhecimento filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .Ordenar("numeroCNPQ", "ASC")
                .Buscar();
    }

    @Override
    public AreaConhecimento Abrir(String numeroCNPQ) {
        return IgualA("numeroCNPQ", numeroCNPQ).Abrir();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Orcamento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrcamentoRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name = "OrcamentoRepositorio")
public class OrcamentoDAO
        extends DAOGenerico<Orcamento>
        implements OrcamentoRepositorio {

    public OrcamentoDAO() {
        super(Orcamento.class);
    }

    @Override
    public List<Orcamento> Buscar(Orcamento filtro) {
        return IgualA("id", filtro.getId())
                .IgualA("projeto", filtro.getProjeto())
                .Like("descricao", filtro.getDescricao())
                .Buscar();
    }


}

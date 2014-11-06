/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AtividadeRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class AtividadeDAO
        extends DAOGenerico<Atividade>
        implements AtividadeRepositorio {

    public AtividadeDAO() {
        super(Atividade.class);
    }

    @Override
    public List<Atividade> Buscar(Atividade filtro) {
        return IgualA("id", filtro.getId())
                .IgualA("bolsa", filtro.getBolsa())
                .Like("descricao", filtro.getDescricao())
                .Ordenar("ordem", "ASC")
                .Buscar();
    }


}

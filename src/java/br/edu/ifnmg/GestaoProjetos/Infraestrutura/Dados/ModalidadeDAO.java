/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author HOME
 */
@Stateless(name = "ModalidadeRepositorio")
public class ModalidadeDAO
        extends DAOGenerico<Modalidade>
        implements ModalidadeRepositorio {

    public ModalidadeDAO() {
        super(Modalidade.class);
    }

    @Override
    public List<Modalidade> Buscar(Modalidade filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("sigla", filtro.getSigla())
                .Ordenar("nome", "ASC")
                .Buscar();
    }

    @Override
    public Modalidade Abrir(String sigla) {
        return IgualA("sigla", sigla).Abrir();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name = "OrientadorRepositorio")
public class OrientadorDAO
        extends DAOGenerico<Orientador>
        implements OrientadorRepositorio {

    public OrientadorDAO() {
        super(Orientador.class);
    }

    @Override
    public List<Orientador> Buscar(Orientador filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("matriculaSiape", filtro.getMatriculaSiape())
                .Buscar();
    }

    @Override
    public Orientador Abrir(String cpf) {
        return IgualA("cpf", cpf).Abrir();
    }

}

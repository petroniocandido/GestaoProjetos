/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Usuario;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.UsuarioRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name = "UsuarioRepositorio")
public class UsuarioDAO
        extends DAOGenerico<Usuario>
        implements UsuarioRepositorio {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> Buscar(Usuario filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("cpf", filtro.getCpf())
                .Buscar();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name = "ProjetoRepositorio")
public class ProjetoDAO
        extends DAOGenerico<Projeto>
        implements ProjetoRepositorio {

    public ProjetoDAO() {
        super(Projeto.class);
    }

    @Override
    public List<Projeto> Buscar(Projeto filtro) {
        return IgualA("id", filtro.getId())
                .Like("titulo", filtro.getTitulo())
                .IgualA("numeroCadastro", filtro.getNumeroCadastro())
                .Ordenar("titulo", "ASC")
                .Buscar();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name= "AgenciaFinanciadoraRepositorio")
public class AgenciaFinanciadoraDAO

    extends DAOGenerico<AgenciaFinanciadora>
    implements AgenciaFinanciadoraRepositorio {

    public AgenciaFinanciadoraDAO() {
        super(AgenciaFinanciadora.class);
    }
    
    @Override
    public List<AgenciaFinanciadora> Buscar(AgenciaFinanciadora obj) {
        return IgualA("id", obj.getId())
                .Like("nome", obj.getNome())
                .IgualA("sigla", obj.getSigla())
                .Buscar();
    }
    
}


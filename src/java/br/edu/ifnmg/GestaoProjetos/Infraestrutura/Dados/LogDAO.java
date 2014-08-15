/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Log;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.LogRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author petronio
 */
@Singleton
public class LogDAO 
    extends DAOGenerico<Log> 
    implements LogRepositorio {

    public LogDAO(){
        super(Log.class);
    }
    
    @Override
    public List<Log> Buscar(Log filtro) {
       return MaiorOuIgualA("dataEvento", filtro.getDataEvento())
                .IgualA("usuario", filtro.getUsuario())
                .IgualA("permissao", filtro.getPermissao())    
                .Ordenar("dataEvento", "desc")
                .Buscar();
               
    }
    
}

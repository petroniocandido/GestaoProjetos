/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadoraRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

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
        String sql = "select a from AgenciaFinanciadora a";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "a.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.nome like '%" + obj.getNome() + "%'"; 
            }
            if(obj.getSigla() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.sigla like '%" + obj.getSigla() + "%'"; 
            }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
}


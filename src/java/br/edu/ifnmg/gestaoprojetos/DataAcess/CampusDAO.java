/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Campus;
import br.edu.ifnmg.gestaoprojetos.DomainModel.CampusRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name="CampusRepositorio")
public class CampusDAO 

    extends DAOGenerico<Campus>
    implements CampusRepositorio {
    
    public CampusDAO() {
        super(Campus.class);
    }
    
    @Override
    public List<Campus> Buscar(Campus obj) {
        String sql = "select c from Campus c";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "c.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "c.nome like '%" + obj.getNome() + "%'"; 
            }
            
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
    
    
}

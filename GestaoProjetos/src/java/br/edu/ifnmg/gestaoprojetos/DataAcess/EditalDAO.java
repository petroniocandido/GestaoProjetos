/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name="EditalRepositorio")
public class EditalDAO 
    
    extends DAOGenerico<Edital>
    implements EditalRepositorio{
    
    public EditalDAO() {
        super(Edital.class);
    }
    
    @Override
    public List<Edital> Buscar(Edital obj) {
        String sql = "select a from Edital a";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "a.id = " + obj.getId();
            }
          // if(obj.getNumero() != null){  
            //    if(filtros.length() > 0) TIPO INT IMCOMPATIVEL CM NULL
              //      filtros += " and ";
                //filtros += "a.nome like '%" + obj.getNumero() + "%'"; 
         //   }
            if(obj.getAgenciaFinanciadora() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.agenciafinanciadora like '%" + obj.getAgenciaFinanciadora() + "%'"; 
            }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Orientador;
import br.edu.ifnmg.gestaoprojetos.DomainModel.OrientadorRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name="OrientadorRepositorio")
public class OrientadorDAO 

    extends DAOGenerico<Orientador>
    implements OrientadorRepositorio{
    
    public OrientadorDAO() {
        super(Orientador.class);
    }
    
    @Override
    public List<Orientador> Buscar(Orientador obj) {
        String sql = "select o from Orientador o";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null && obj.getId() > 0 ){
                filtros += "o.id = " + obj.getId();
            }
            
        if(obj.getMatriculaSiape() > 0){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "o.matriculaSiape = " + obj.getMatriculaSiape() ; 
            }
            
            
         if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "o.nome like '%" + obj.getNome() + "%'"; 
            }
            
         
          }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
}


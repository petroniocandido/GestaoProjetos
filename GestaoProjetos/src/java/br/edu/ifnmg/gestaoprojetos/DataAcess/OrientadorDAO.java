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
@Stateless(name="OrientadorRepositorio")
public class OrientadorDAO 

    extends DAOGenerico<Orientador>
    implements OrientadorRepositorio{
    
    public OrientadorDAO() {
        super(Orientador.class);
    }
    
    @Override
    public List<Orientador> Buscar(Orientador obj) {
        String sql = "select a from Orientador a";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "a.id = " + obj.getId();
            }
            
            //ver se a busca por nome é em USUARIODAO
            
            //FAZER A BUSCA POR MATRICULA SIAPE
            
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.nome like '%" + obj.getNome() + "%'"; 
            }
            if(obj.getAreaConhecimento() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.areaconhecimento like '%" + obj.getAreaConhecimento() + "%'"; 
            }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
}

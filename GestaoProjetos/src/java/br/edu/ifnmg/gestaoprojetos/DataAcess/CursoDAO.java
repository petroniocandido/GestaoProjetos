/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DataAcess.DAOGenerico;
import javax.ejb.Stateless;
import br.edu.ifnmg.gestaoprojetos.DomainModel.*;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Curso;
import br.edu.ifnmg.gestaoprojetos.DomainModel.CursoRepositorio;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name= "CursoRepositorio")
public class CursoDAO

    extends DAOGenerico<Curso>
    implements CursoRepositorio{
    
     public CursoDAO() {
        super(Curso.class);
    }
     
     @Override
    public List<Curso> Buscar(Curso obj) {
        String sql = "select a from Curso a";
        
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

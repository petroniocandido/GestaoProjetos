/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Curso;
import br.edu.ifnmg.gestaoprojetos.DomainModel.CursoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
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
        String sql = "select c from Curso c";
        
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
            if(obj.getSigla() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "c.sigla like '%" + obj.getSigla() + "%'"; 
            }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
     
     
    
}


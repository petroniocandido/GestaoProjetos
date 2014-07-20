/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Aluno;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AlunoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name= "AlunoRepositorio")
public class AlunoDAO 

    extends DAOGenerico<Aluno>
    implements AlunoRepositorio {     
    
    
    public AlunoDAO() {
        super(Aluno.class);
    }
    
     @Override
    public List<Aluno> Buscar(Aluno obj) {
        String sql = "select a from Aluno a";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "a.id = " + obj.getId();
            }
            
        if(obj.getMatricula() > 0){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.matricula = " + obj.getMatricula() ; 
            }
            
           
        if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.nome like '%" + obj.getNome() + "%'"; 
            }
        
         if(obj.getCurso() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.curso like '%" + obj.getCurso() + "%'"; 
            }
        
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
  
    
}

    



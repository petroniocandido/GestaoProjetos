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
@Stateless(name="ProjetoRepositorio")
public class ProjetoDAO
    
    extends DAOGenerico<Projeto>
    implements ProjetoRepositorio{
    
    public ProjetoDAO() {
        super(Projeto.class);
    }
    
    @Override
    public List<Projeto> Buscar(Projeto obj) {
        String sql = "select a from Projeto a";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "a.id = " + obj.getId();
            }
            if(obj.getTitulo() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.titulo like '%" + obj.getTitulo() + "%'"; 
            }
            //if(obj.getNumeroCadastro() != null){
              //  if(filtros.length() > 0)
                //    filtros += " and ";
               // filtros += "a.sigla like '%" + obj.getNumeroCadastro() + "%'"; 
            //}
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
    
}

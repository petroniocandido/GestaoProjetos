/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import br.edu.ifnmg.gestaoprojetos.DomainModel.UsuarioRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name="UsuarioRepositorio")
public class UsuarioDAO

    extends DAOGenerico<Usuario>
    implements UsuarioRepositorio{
    
     public UsuarioDAO() {
        super(Usuario.class);
    }
    
     @Override
    public List<Usuario> Buscar(Usuario obj) {
        String sql = "select u from Usuario u";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "u.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "u.nome like '%" + obj.getNome() + "%'"; 
            }
            //if(obj.getCpf() != null){
              //  if(filtros.length() > 0)
                //    filtros += " and ";
               // filtros += "a.cpf like '%" + obj.getCpf() + "%'"; 
           // }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
}


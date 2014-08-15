/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Entidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Repositorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Isla Guedes
 */
public abstract class DAOGenerico<T extends Entidade> implements Repositorio<T> {
    @PersistenceContext(name="GestaoProjetosPU")
    protected EntityManager manager;
    private Class tipo;
    public DAOGenerico (Class t) {
        tipo = t;
    }
    
    @Override
    public T Refresh(Long id) {
        manager.flush();
        return (T) manager.getReference(tipo, id);
    }
    
    @Override
    public boolean Salvar(T obj) {
        try{
            if(manager.contains(obj) || (obj.getId() != null && obj.getId() > 0)) {
                obj = manager.merge(obj);
            }
            else {
                manager.persist(obj);
            }
            
            manager.flush();
            
            return true;
        }catch (Exception ex){
        System.out.println(ex.getMessage());
        return false;
        }
    }
    
          
    
    @Override
    public T Abrir(Long id) {
        try {
            T obj = (T) manager.find(tipo, id);
            return obj;
            //abrir
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    @Override
      public abstract List<T> Buscar(T obj);

    @Override
    public boolean Apagar(T obj) {
        try {
            manager.remove(manager.merge(obj));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.DataAccess.DAOGenerico;
import br.edu.ifnmg.DomainModel.Entidade;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author petronio
 * @param <T>
 */
public class DAO<T extends Entidade> extends DAOGenerico<T> {

    public DAO(Class t) {
        super(t);
    }
    
    @PersistenceContext(name = "GestaoProjetosPU")
    private EntityManager manager;
    
    @Override
    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }
    
    
    
}

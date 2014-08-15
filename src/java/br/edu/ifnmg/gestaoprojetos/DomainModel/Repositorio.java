/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.util.List;

/**
 *
 * @author Isla Guedes
 */
public interface Repositorio<T> {
    
    boolean Salvar(T obj);
    boolean Apagar(T obj);
    T Abrir(Long id);
    T Refresh(Long id);
    List<T> Buscar(T obj);
    
}


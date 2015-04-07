/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Infraestrutura.TextSearch;

import java.util.List;

/**
 *
 * @author petronio
 */
public interface Indice<T> {
    String getCaminho();
    void indexar(T obj);
    List<T> buscar(T obj);
    List<T> buscar(String campo, String expressao);
}

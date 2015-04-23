/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Entidade;
import java.util.List;

/**
 *
 * @author petronio
 * @param <T>
 */

public interface Indice<T extends Entidade> {
    void indexacaoGeral();
    String getCaminho();
    void indexar(T obj);
    List<T> buscar(T obj);
    List<T> buscar(String campo, String expressao);
}

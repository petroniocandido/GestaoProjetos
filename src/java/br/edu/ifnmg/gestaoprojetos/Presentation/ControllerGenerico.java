/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import java.util.List;

/**
 *
 * @author Isla Guedes
 */
public abstract class ControllerGenerico<T> {
    
    public abstract void salvar();
    public abstract String novo();
    public abstract String abrir();
    public abstract String cancelar();
    public abstract String excluir();
    public abstract void filtrar();
    
    T entidade, filtro;
    List<T> listagem;

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T enditade) {
        this.entidade = enditade;
    }

    public T getFiltro() {
        return filtro;
    }

    public void setFiltro(T filtro) {
        this.filtro = filtro;
    }

    public List<T> getListagem() {
        if(listagem == null) filtrar();
        return listagem;
    }

    public void setListagem(List<T> listagem) {
        this.listagem = listagem;
    }
    
    
    
}


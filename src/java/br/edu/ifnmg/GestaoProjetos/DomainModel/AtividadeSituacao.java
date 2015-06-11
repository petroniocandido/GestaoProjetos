/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.DomainModel;

/**
 *
 * @author petronio
 */
public enum AtividadeSituacao {
    Pendente(0, "Pendente", "red"),
    EmExecucao(1, "Em Execução", "yellow"),
    Concluido(2, "Concluída Satisfatoriamente", "greenyellow"),
    NaoConcluido(3, "Não Concluído", "red");
    
    private int id;
    private String descricao;
    private String cor;

    private AtividadeSituacao(int id, String descricao, String cor) {
        this.id = id;
        this.descricao = descricao;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }
    
    

    @Override
    public String toString() {
        return descricao;
    }
    
    
}

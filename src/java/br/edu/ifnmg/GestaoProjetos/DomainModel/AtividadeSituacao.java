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
    Pendente(0, "Pendente"),
    EmExecucao(1, "Em Execução"),
    Concluido(2, "Concluído");    
    private int id;
    private String descricao;

    private AtividadeSituacao(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return descricao;
    }
}

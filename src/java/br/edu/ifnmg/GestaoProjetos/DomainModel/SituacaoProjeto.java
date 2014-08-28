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
public enum SituacaoProjeto {
    Cadastrado(0, "Cadastrado"),
    Aprovado(1, "Aprovado"),
    Reprovado(2, "Reprovado"),
    EmExecucao(3, "Em Execução"),
    Cancelado(4, "Cancelado"),
    Concluido(5, "Concluído");    
    private int id;
    private String descricao;

    private SituacaoProjeto(int id, String descricao) {
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

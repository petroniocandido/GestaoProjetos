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
public enum ProjetoTipo {
    Ensino("Ensino"),
    Extensao("Extensão"),
    InovacaoTecnologica("Inovação Tecnológica"),
    Pesquisa("Pesquisa");
    
    private ProjetoTipo(String descricao) {
        this.descricao = descricao;
    }
    
    private final String descricao;

    public String getDescricao() {
        return descricao;
    }
    
    
}

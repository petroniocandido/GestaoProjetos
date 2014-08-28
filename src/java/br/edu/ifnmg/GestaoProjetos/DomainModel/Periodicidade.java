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
public enum Periodicidade {
    Unico(false,0),
    Diario(true,1),
    Semanal(true,7),
    Quinzenal(true,15),
    Mensal(true,30),
    Bimestral(true,60),
    Trimestral(true,30),
    Quadrimestral(true,120),
    Semestral(true,180),
    Anual(true,360);
    
    private boolean repete;
    private int dias;

    private Periodicidade(boolean repete, int dias) {
        this.repete = repete;
        this.dias = dias;
    }

    public boolean isRepete() {
        return repete;
    }

    public void setRepete(boolean repete) {
        this.repete = repete;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
    
}

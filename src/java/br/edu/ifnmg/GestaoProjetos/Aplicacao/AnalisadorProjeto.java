/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Aplicacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.ProjetoSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Status;

/**
 *
 * @author petronio
 */
public class AnalisadorProjeto {
    public void analisar(Projeto p){
        if(p.getSituacao() == ProjetoSituacao.Cancelado || p.getSituacao() == ProjetoSituacao.Reprovado)
            return;
        
        p.setStatus(Status.Regular);
        
        
        
        
    }
    
    
    
}

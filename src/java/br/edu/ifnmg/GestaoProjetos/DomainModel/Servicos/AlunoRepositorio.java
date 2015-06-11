/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.DomainModel.Services.Repositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import javax.ejb.Local;

/**
 *
 * @author Isla Guedes
 */
@Local
public interface AlunoRepositorio extends Repositorio<Aluno> {        
     public Aluno AbrirPorCPF(String cpf);
}


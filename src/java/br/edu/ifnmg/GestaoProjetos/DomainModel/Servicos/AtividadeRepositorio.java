/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.DomainModel.Services.Repositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import javax.ejb.Local;

/**
 *
 * @author Isla Guedes
 */
@Local
public interface AtividadeRepositorio  extends Repositorio<Atividade> {
    
}

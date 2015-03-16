/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Isla Guedes
 */
@Local
public interface BolsaRepositorio extends Repositorio<Bolsa> {        
     List<Bolsa> doOrientando(Aluno a);
     List<Bolsa> doOrientador(Orientador a);
}


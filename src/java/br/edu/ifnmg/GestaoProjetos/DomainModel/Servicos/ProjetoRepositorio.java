/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.DomainModel.Services.Repositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Isla Guedes
 */
@Local
public interface ProjetoRepositorio extends Repositorio<Projeto>  {
     List<Projeto> doOrientador(Orientador o);
     List<Projeto> doOrientando(Aluno o);
}


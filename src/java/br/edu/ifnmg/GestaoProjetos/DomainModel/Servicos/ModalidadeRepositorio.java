/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import javax.ejb.Local;

/**
 *
 * @author HOME
 */
@Local
public interface ModalidadeRepositorio  extends Repositorio<Modalidade> {
    public Modalidade Abrir(String sigla);
}

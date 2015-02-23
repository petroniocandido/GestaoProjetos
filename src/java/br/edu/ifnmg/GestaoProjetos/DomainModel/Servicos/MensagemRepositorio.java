/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Mensagem;
import br.edu.ifnmg.GestaoProjetos.DomainModel.MensagemPerfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author petronio
 */
@Local
public interface MensagemRepositorio extends Repositorio<Mensagem> {
    List<Mensagem> porPerfil(MensagemPerfil perfil);
}

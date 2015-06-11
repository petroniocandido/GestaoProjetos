/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaProjetoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author petronio
 */
@Singleton
public class PessoaProjetoDAO
        extends DAO<PessoaProjeto>
        implements PessoaProjetoRepositorio {

    public PessoaProjetoDAO() {
        super(PessoaProjeto.class);
    }

    @Override
    public List<PessoaProjeto> Buscar(PessoaProjeto filtro) {
        if (filtro != null) {
            IgualA("id", filtro.getId())
                    .Like("nome", filtro.getNome())
                    .Like("cpf", filtro.getCpf())
                    .Like("email", filtro.getEmail());
        }
        Ordenar("nome", "ASC");
        return Buscar();
    }
    

    @Override
    public PessoaProjeto Abrir(String login) {
        return IgualA("email", login).Abrir();
    }

    @Override
    public PessoaProjeto AbrirPorCPF(String cpf) {
        return IgualA("cpf", cpf.replace(".", "").replace("-", "")).Abrir();
    }

}

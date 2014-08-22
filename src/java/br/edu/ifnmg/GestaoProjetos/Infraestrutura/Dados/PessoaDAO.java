/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author petronio
 */
@Singleton
public class PessoaDAO
        extends DAOGenerico<Pessoa>
        implements PessoaRepositorio {

    public PessoaDAO() {
        super(Pessoa.class);
    }

    @Override
    public List<Pessoa> Buscar(Pessoa filtro) {
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
    public Pessoa Abrir(String login) {
        return IgualA("email", login).Abrir();
    }

    @Override
    public Pessoa AbrirPorCPF(String cpf) {
        return IgualA("cpf", cpf.replace(".", "").replace("-", "")).Abrir();
    }

}

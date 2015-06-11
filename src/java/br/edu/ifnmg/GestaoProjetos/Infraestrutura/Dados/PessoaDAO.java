/*
 *   This file is part of SGEA - Sistema de Gestão de Eventos Acadêmicos - TADS IFNMG Campus Januária.
 *
 *   SGEA is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SGEA is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SGEA.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.DomainModel.Pessoa;
import br.edu.ifnmg.DomainModel.Services.PessoaRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Atividade;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.Query;

/**
 *
 * @author petronio
 */
@Singleton
public class PessoaDAO
        extends DAO<Pessoa>
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
        return IgualA("cpf", cpf).Abrir();
    }

}

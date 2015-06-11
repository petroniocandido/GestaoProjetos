/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class AlunoDAO 

    extends DAO<Aluno>
    implements AlunoRepositorio {     
    
    
    public AlunoDAO() {
        super(Aluno.class);
    }
    
     @Override
    public List<Aluno> Buscar(Aluno filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("matricula", filtro.getMatricula())
                .IgualA("cpf", filtro.getCpf())
                .IgualA("campus", filtro.getCampus())
                .IgualA("curso", filtro.getCurso())
                .Ordenar("nome", "ASC")
                .Buscar();
    }

    @Override
    public Aluno AbrirPorCPF(String cpf) {
        return IgualA("cpf", cpf.replace(".", "").replace("-", "")).Abrir();
    }
    
}

    



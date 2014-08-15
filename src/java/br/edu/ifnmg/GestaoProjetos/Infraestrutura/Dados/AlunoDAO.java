/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name= "AlunoRepositorio")
public class AlunoDAO 

    extends DAOGenerico<Aluno>
    implements AlunoRepositorio {     
    
    
    public AlunoDAO() {
        super(Aluno.class);
    }
    
     @Override
    public List<Aluno> Buscar(Aluno filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .IgualA("matricula", filtro.getMatricula())
                .IgualA("curso", filtro.getCurso())
                .Buscar();
    }
    
}

    



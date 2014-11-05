/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class ProjetoDAO
        extends DAOGenerico<Projeto>
        implements ProjetoRepositorio {

    public ProjetoDAO() {
        super(Projeto.class);
    }

    @Override
    public List<Projeto> Buscar(Projeto filtro) {
        return IgualA("id", filtro.getId())
                .Like("titulo", filtro.getTitulo())
                .IgualA("numeroCadastro", filtro.getNumeroCadastro())
                .IgualA("modalidade", filtro.getModalidade())
                .IgualA("edital", filtro.getEdital())
                .IgualA("agenciaFinanciadora", filtro.getAgenciaFinanciadora())
                .IgualA("campus", filtro.getCampus())
                .Ordenar("titulo", "ASC")
                .Buscar();
    }

    @Override
    public List<Projeto> doOrientador(Orientador o) {
        return IgualA("coordenador", o)
                .Ordenar("dataTermino", "Desc")
                .Buscar();
    }

    @Override
    public List<Projeto> doOrientando(Aluno o) {
        return Join("orientandos", "a")
                .IgualA("a.id", o.getId())
                .Ordenar("dataTermino", "Desc")
                .Buscar();
    }
    
    

}

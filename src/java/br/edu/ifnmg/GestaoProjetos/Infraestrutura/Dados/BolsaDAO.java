/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.BolsaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class BolsaDAO
        extends DAOGenerico<Bolsa>
        implements BolsaRepositorio {

    public BolsaDAO() {
        super(Bolsa.class);
    }

    @Override
    public List<Bolsa> Buscar(Bolsa filtro) {
        return IgualA("id", filtro.getId())
                .IgualA("projeto", filtro.getProjeto())
                .IgualA("modalidade", filtro.getModalidade())
                .IgualA("edital", filtro.getEdital())
                .IgualA("orientando", filtro.getOrientando())
                .IgualA("situacao", filtro.getSituacao())
                .IgualA("agenciafinanciadora", filtro.getAgenciaFinanciadora())
                .Buscar();
    }

    @Override
    public List<Bolsa> doOrientando(Aluno a) {
        return IgualA("orientando", a)
                .Buscar();
    }
    
    @Override
    public List<Bolsa> doOrientador(Orientador a) {
        return Join("projeto","p")
                .IgualA("p.coordenador", a)
                .Buscar();
    }

}

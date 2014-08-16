/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "agenciaFinanciadoraConverter")
@Singleton
public class AgenciaFinanciadoraConverter
        extends GenericConverter<AgenciaFinanciadora, AgenciaFinanciadoraRepositorio>
        implements Serializable {

    @EJB
    AgenciaFinanciadoraRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<AgenciaFinanciadora> autoCompleteAgenciaFinanciadora(String query) {
        AgenciaFinanciadora i = new AgenciaFinanciadora();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

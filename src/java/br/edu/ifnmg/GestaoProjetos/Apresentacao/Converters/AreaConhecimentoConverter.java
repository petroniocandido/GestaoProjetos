/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "areaConhecimentoConverter")
@Singleton
public class AreaConhecimentoConverter
        extends GenericConverter<AreaConhecimento, AreaConhecimentoRepositorio>
        implements Serializable {

    @EJB
    AreaConhecimentoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<AreaConhecimento> autoCompleteAreaConhecimento(String query) {
        AreaConhecimento i = new AreaConhecimento();
        i.setNome(query);
        return dao.Buscar(i);
    }
}

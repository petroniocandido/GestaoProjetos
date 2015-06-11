/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.DomainModel.Arquivo;
import br.edu.ifnmg.DomainModel.Services.ArquivoRepositorio;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author petronio
 */
@Named(value = "arquivoConverter")
@Singleton
public class ArquivoConverter
        extends GenericConverter<Arquivo, ArquivoRepositorio>
        implements Serializable {
    
      public List<Arquivo> autoCompleteArquivo(String query) {
        Arquivo i = new Arquivo();
        i.setNome(query);
        return dao.Buscar(i);
    }

    @EJB
    ArquivoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
}

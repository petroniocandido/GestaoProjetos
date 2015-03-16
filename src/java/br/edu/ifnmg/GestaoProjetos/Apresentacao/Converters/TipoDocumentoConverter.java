/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.TipoDocumentoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Singleton;

@Named(value = "tipoDocumentoConverter")
@Singleton
public class TipoDocumentoConverter
        extends GenericConverter<DocumentoTipo, TipoDocumentoRepositorio>
        implements Serializable {

    @EJB
    TipoDocumentoRepositorio dao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<DocumentoTipo> autoCompleteTipoDocumento(String query) {
        DocumentoTipo i = new DocumentoTipo();
        i.setNome(query);
        return dao.Buscar(i);
    }
}
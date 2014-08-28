/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.TipoDocumentoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HOME
 */
@Stateless(name = "TipoDocumentoRepositorio")
public class TipoDocumentoDAO
        extends DAOGenerico<DocumentoTipo>
        implements TipoDocumentoRepositorio {

    public TipoDocumentoDAO() {
        super(DocumentoTipo.class);
    }

    @Override
    public List<DocumentoTipo> Buscar(DocumentoTipo filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .Ordenar("nome", "ASC")
                .Buscar();

    }

}

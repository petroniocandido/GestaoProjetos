/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.DocumentoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Isla Guedes
 */
@Singleton
public class DocumentoDAO
        extends DAOGenerico<Documento>
        implements DocumentoRepositorio {

    public DocumentoDAO() {
        super(Documento.class);
    }

    @Override
    public List<Documento> Buscar(Documento filtro) {
        return IgualA("id", filtro.getId())
                .IgualA("tipoDocumento", filtro.getTipoDocumento())
                .IgualA("pessoa", filtro.getPessoa())
                .IgualA("projeto", filtro.getProjeto())
                .IgualA("situacao", filtro.getSituacao())
                .Buscar();
    }

}

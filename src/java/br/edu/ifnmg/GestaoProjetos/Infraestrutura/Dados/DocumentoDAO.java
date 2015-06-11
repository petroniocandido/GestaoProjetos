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
        extends DAO<Documento>
        implements DocumentoRepositorio {

    public DocumentoDAO() {
        super(Documento.class);
    }

    @Override
    public List<Documento> Buscar(Documento filtro) {
        IgualA("id", filtro.getId())
                .IgualA("tipoDocumento", filtro.getTipoDocumento())
                .IgualA("pessoa", filtro.getPessoa());

        if (filtro.getProjeto() != null && filtro.getProjeto().getId() != null && filtro.getProjeto().getId() > 0) {
            IgualA("projeto", filtro.getProjeto());
        } else if (filtro.getProjeto() != null) {
            Join("projeto", "p").IgualA("p.campus", filtro.getProjeto().getCampus());
        }
        return IgualA("situacao", filtro.getSituacao())
                .Buscar();
    }

}

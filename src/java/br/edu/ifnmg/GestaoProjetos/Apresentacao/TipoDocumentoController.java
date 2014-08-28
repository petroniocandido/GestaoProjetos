/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Periodicidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoUnidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.TipoDocumentoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author HOME
 */
@Named(value = "tipoDocumentoController")
@RequestScoped
public class TipoDocumentoController 
    extends ControllerBaseEntidade<DocumentoTipo> implements Serializable {

    /**
     * Creates a new instance of DocumentoTipoController
     */
    public TipoDocumentoController() {
    }
    
    @EJB
    TipoDocumentoRepositorio dao;
    
    Periodicidade[] periodicidades;
    
    DocumentoUnidade[] unidades;
    
    @Override
    public DocumentoTipo getFiltro() {
        if (filtro == null) {
            filtro = new DocumentoTipo();
            filtro.setNome(getSessao("tdctrl_nome"));
            filtro.setSigla(getSessao("tdctrl_sigla"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(DocumentoTipo filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("tdctrl_nome", filtro.getNome());
            setSessao("tdctrl_sigla",filtro.getSigla());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarTipoDocumento.xhtml");
        setPaginaListagem("listagemTiposDocumentos.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new DocumentoTipo());
    }

    public Periodicidade[] getPeriodicidades() {
        if(periodicidades == null){
            periodicidades = Periodicidade.values();
        }
        return periodicidades;
    }
    
    public DocumentoUnidade[] getUnidades() {
        if(unidades == null){
            unidades = DocumentoUnidade.values();
        }
        return unidades;
    }
    
    
}

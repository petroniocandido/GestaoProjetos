/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author HOME
 */
@Named(value = "modalidadeController")
@RequestScoped
public class ModalidadeController 
    extends ControllerBaseEntidade<Modalidade> implements Serializable {

    /**
     * Creates a new instance of ModalidadeController
     */
    public ModalidadeController() {
    }
    
    @EJB
    ModalidadeRepositorio dao;
    
    DocumentoTipo documento;
    
    @Override
    public Modalidade getFiltro() {
        if (filtro == null) {
            filtro = new Modalidade();
            filtro.setNome(getSessao("mdctrl_nome"));
            filtro.setSigla(getSessao("mdctrl_sigla"));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Modalidade filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("mdctrl_nome", filtro.getNome());
            setSessao("mdctrl_sigla", filtro.getSigla());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarModalidade.xhtml");
        setPaginaListagem("listagemModalidades.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Modalidade());
    }
    
    public void addDocumento(){
        //entidade = dao.Refresh(entidade.getId());
        entidade.add(documento);
        SalvarAgregado(documento);
        documento = new DocumentoTipo();
    }
    
    public void removeDocumento(){
        //entidade = dao.Refresh(entidade.getId());
        entidade.remove(documento);
        SalvarAgregado(documento);
        documento = new DocumentoTipo();
    }

    public DocumentoTipo getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoTipo documento) {
        this.documento = documento;
    }
    
    
}

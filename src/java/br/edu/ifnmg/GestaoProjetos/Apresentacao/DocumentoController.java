/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Arquivo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoTipo;
import static br.edu.ifnmg.GestaoProjetos.DomainModel.Documento_.arquivo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Documento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoSituacao;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.DocumentoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.TipoDocumentoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.UsuarioTipo;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "documentoController")
@RequestScoped
public class DocumentoController 
    extends ControllerBaseEntidade<Documento> implements Serializable {
    public DocumentoController() {        
    }    
    
    @EJB
    DocumentoRepositorio dao;
    
    @EJB
    TipoDocumentoRepositorio daoTipo;
    
    @EJB
    PessoaRepositorio daoPessoa;
    
    @EJB
    ProjetoRepositorio daoProjeto;
    
    List<DocumentoTipo> tipos;
    
    DocumentoSituacao[] situacoes;
    
    @Override
    public Documento getFiltro() {
        if (filtro == null) {
            filtro = new Documento();
            filtro.setPessoa((Pessoa)getSessao("docctrl_pessoa", daoPessoa));
            filtro.setProjeto((Projeto)getSessao("docctrl_proj", daoProjeto));
            filtro.setTipoDocumento((DocumentoTipo)getSessao("docctrl_tipo", daoTipo));
            filtro.setDataPrevista(getSessaoData("docctrl_dtprevista"));            
            filtro.setDataEfetiva(getSessaoData("docctrl_dtprevista"));
            filtro.setSituacao(DocumentoSituacao.valueOf(getSessao("docctrl_sit")));
        }
        return filtro;
    }

    @Override
    public void setFiltro(Documento filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("docctrl_pessoa", filtro.getPessoa());
            setSessao("docctrl_proj", filtro.getProjeto());
            setSessao("docctrl_tipo", filtro.getTipoDocumento());
            setSessao("docctrl_dtprevista",filtro.getDataPrevista());            
            setSessao("docctrl_dtprevista",filtro.getDataEfetiva());
            setSessao("docctrl_sit",filtro.getSituacao().name());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        if(getUsuarioCorrente().getUsuarioTipo() == UsuarioTipo.Pessoa)
            setPaginaEdicao("editarDocumento.xhtml");
        else
            setPaginaEdicao("visualizarDocumento.xhtml");
        setPaginaListagem("listagemEditais.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Documento());
    }
    
    public void fileUploadListener(FileUploadEvent event) {  
        entidade = dao.Refresh(getEntidade());
        Arquivo tmp = criaArquivo(event.getFile());
        
        entidade.setArquivo(tmp);
        
        if(dao.Salvar(entidade)){
            Mensagem("Sucesso", "Arquivo anexado com êxito!");
            AppendLog("Anexou o arquivo " + tmp + " ao evento " + entidade);
        } else {
            Mensagem("Falha", "Falha ao anexar o arquivo!");
            AppendLog("Erro ao anexar o arquivo " + tmp + " ao evento " + entidade + ":" + dao.getErro());
        }        
    }
    
    
    public List<DocumentoTipo> getTipos() {
        if (tipos == null) {
            tipos = daoTipo.Ordenar("nome", "ASC").Buscar();
        }               
        return tipos;
    }

    public DocumentoSituacao[] getSituacoes() {
        if(situacoes == null)
            situacoes = DocumentoSituacao.values();
        return situacoes;
    }
    
    

}

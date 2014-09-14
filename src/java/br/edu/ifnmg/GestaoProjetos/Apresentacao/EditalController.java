/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.DocumentoTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "editalController")
@RequestScoped
public class EditalController 
    extends ControllerBaseEntidade<Edital> implements Serializable {

    List<AgenciaFinanciadora> listagemAgencia;
    
    DocumentoTipo documento;
        
    
    /**
     * Creates a new instance of EditalController
     */
    public EditalController() {        
    }    
    
    @EJB
    EditalRepositorio dao;
    
    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;
    
    @EJB
    CampusRepositorio daoCampus;
    
    @Override
    public Edital getFiltro() {
        if (filtro == null) {
            filtro = new Edital();
            filtro.setNumero(getSessao("edctrl_numero") != null ? Integer.parseInt(getSessao("edctrl_numero")) : 0);
            filtro.setSigla(getSessao("edctrl_sigla"));
            filtro.setAgenciaFinanciadora((AgenciaFinanciadora)getSessao("edctrl_agencia", daoAgencia));
            filtro.setCampus((Campus)getSessao("edctrl_campus", daoAgencia));
            if(filtro.getCampus() == null){
                filtro.setCampus(getUsuarioCorrente().getCampus());
            }
        }
        return filtro;
    }

    @Override
    public void setFiltro(Edital filtro) {
        this.filtro = filtro;
        if (filtro != null) {
            setSessao("edctrl_numero",Integer.toString(filtro.getNumero()));
            setSessao("edctrl_sigla",filtro.getSigla());
            setSessao("edctrl_agencia", filtro.getAgenciaFinanciadora());
            setSessao("edctrl_campus", filtro.getCampus());
        }
    }

    @PostConstruct
    public void init() {
        setRepositorio(dao);
        setPaginaEdicao("editarEdital.xhtml");
        setPaginaListagem("listagemEditais.xhtml");
    }
    
    @Override
    public void limpar() {
        setEntidade(new Edital());
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
    
    public List<AgenciaFinanciadora> getListagemAgencia() {
        if (listagemAgencia == null) {
            Edital filtro = new Edital();
            listagemAgencia = daoAgencia.Buscar(null);
        }       
        
        return listagemAgencia;
    }

    public void setListagemAgencia(List<AgenciaFinanciadora> listagemAgencia) {
        this.listagemAgencia = listagemAgencia;
    }

    public DocumentoTipo getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoTipo documento) {
        this.documento = documento;
    }

   

}

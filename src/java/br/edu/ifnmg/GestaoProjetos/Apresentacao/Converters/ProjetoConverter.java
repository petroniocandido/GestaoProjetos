/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ProjetoRepositorio;
import br.edu.ifnmg.GestaoProjetos.Infraestrutura.AutenticacaoService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author HOME
 */
@Named(value = "projetoConverter")
@Singleton
public class ProjetoConverter
        extends GenericConverter<Projeto, ProjetoRepositorio>
        implements Serializable {

    @EJB
    ProjetoRepositorio dao;
    
    @Inject
    protected AutenticacaoService autenticacao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Projeto> autoCompleteProjeto(String query) {
        Projeto i = new Projeto();
        i.setTitulo(query);
        i.setCampus(autenticacao.getUsuarioCorrente().getCampus());
        return dao.Buscar(i);
    }
}

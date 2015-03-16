/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Converters;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.GenericConverter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import br.edu.ifnmg.GestaoProjetos.Infraestrutura.AutenticacaoService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Singleton;

@Named(value = "orientadorConverter")
@Singleton
public class OrientadorConverter
        extends GenericConverter<Orientador, OrientadorRepositorio>
        implements Serializable {

    @EJB
    OrientadorRepositorio dao;
    
    @Inject
    protected AutenticacaoService autenticacao;

    @PostConstruct
    public void init() {
        setRepositorio(dao);
    }
    
    public List<Orientador> autoCompleteOrientador(String query) {
        Orientador i = new Orientador();
        i.setCampus(autenticacao.getUsuarioCorrente().getCampus());
        i.setNome(query);
        return dao.Buscar(i);
    }
}

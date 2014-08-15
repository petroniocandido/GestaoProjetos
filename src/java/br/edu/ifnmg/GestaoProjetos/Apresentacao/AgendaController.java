/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseEntidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author petronio
 */
@Named(value = "agendaController")
@RequestScoped
public class AgendaController
        extends ControllerBaseEntidade<Projeto>
        implements Serializable {

    private ScheduleModel eventModel;

    /**
     * Creates a new instance of FuncionarioBean
     */
    public AgendaController() {

    }

    @PostConstruct
    public void init() {
//        setRepositorio(dao);
    }


    public ScheduleModel getEventModel() {
        if (eventModel == null) {
            eventModel = new DefaultScheduleModel();
        }
        return eventModel;
    }


    @Override
    public void salvar() {
    }

    @Override
    public String apagar() {
        return "";
    }

    @Override
    public String abrir() {
        return "";
    }

    @Override
    public String cancelar() {
        return "";
    }

    @Override
    public String novo() {
        limpar();
        return "";
    }

    @Override
    public void limpar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

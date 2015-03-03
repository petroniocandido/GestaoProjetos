/*
 *   This file is part of SGEA - Sistema de Gestão de Eventos Acadêmicos - TADS IFNMG Campus Januária.
 *
 *   SGEA is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SGEA is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SGEA.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao.Relatorios;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBaseRelatorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.BolsaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author petronio
 */
@Named(value = "listagemBolsasController")
@RequestScoped
public class ListagemBolsasController
        extends ControllerBaseRelatorio<Bolsa>
        implements Serializable {

    /**
     * Creates a new instance of FuncionarioBean
     */
    public ListagemBolsasController() {
        setArquivoSaida("ListagemBolsas");
        setRelatorio("ListagemBolsas.jasper");
        filtro = new Bolsa();
    }

    @EJB
    BolsaRepositorio daoBolsa;

    Bolsa filtro;

    @Override
    protected Map<String, Object> carregaParametros() {
        try {

            Map<String, Object> tmp = getParametrosComuns();
            tmp.put("data", new Date());
            return tmp;
        } catch (MalformedURLException ex) {
            return new HashMap<>();
        }
    }

    @Override
    public List<Bolsa> getDados() {
        return daoBolsa
                .IgualA("edital", filtro.getEdital())
                .Ordenar("modalidade", "ASC")
                .Buscar();
    }

    public Bolsa getFiltro() {
        return filtro;
    }

    public void setFiltro(Bolsa filtro) {
        this.filtro = filtro;
    }

}

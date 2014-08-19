/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author petronio
 */
public class ProjetoCSVImporter extends CSVImporter<Projeto> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");

    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;

    @EJB
    CampusRepositorio daoCampus;

    @EJB
    OrientadorRepositorio daoCoordenador;

    @EJB
    EditalRepositorio daoEdital;

    @EJB
    ModalidadeRepositorio daoModalidade;

    @Override
    protected Projeto gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Projeto obj = new Projeto();
        obj.setTitulo(colunas[cabecalho.get("titulo")]);

        if (cabecalho.containsKey("AgenciaFinanciadora")) {
            AgenciaFinanciadora tmp = daoAgencia.Abrir(colunas[cabecalho.get("AgenciaFinanciadora")]);
            obj.setAgenciaFinanciadora(tmp);
        }

        if (cabecalho.containsKey("Campus")) {
            Campus tmp = daoCampus.Abrir(colunas[cabecalho.get("Campus")]);
            obj.setCampus(tmp);
        }

        if (cabecalho.containsKey("Coordenador")) {
            Orientador tmp = daoCoordenador.Abrir(colunas[cabecalho.get("Coordenador")]);
            obj.setCoordenador(tmp);
        }

        if (cabecalho.containsKey("DataInicio")) {            
            try {
                obj.setDataInicio(df.parse(colunas[cabecalho.get("DataInicio")]));
            } catch (ParseException ex) {
                Logger.getLogger(ProjetoCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (cabecalho.containsKey("DataTermino")) {
            try {
                obj.setDataTermino(df.parse(colunas[cabecalho.get("DataTermino")]));
            } catch (ParseException ex) {
                Logger.getLogger(ProjetoCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cabecalho.containsKey("Edital")) {
            Edital tmp = daoEdital.Abrir(colunas[cabecalho.get("Edital")]);
            obj.setEdital(tmp);
        }

        if (cabecalho.containsKey("Modalidade")) {
            Modalidade tmp = daoModalidade.Abrir(colunas[cabecalho.get("Modalidade")]);
            obj.setModalidade(tmp);
        }
        if (cabecalho.containsKey("NumeroCadastro")) {
            int num = Integer.parseInt(colunas[cabecalho.get("AgenciaFinanciadora")]);
            obj.setNumeroCadastro(num);
        }
        if (cabecalho.containsKey("ValorFinanciamento")) {
            BigInteger tmp = new BigInteger(colunas[cabecalho.get("AgenciaFinanciadora")]);
            obj.setValorFinanciamento(tmp);
        }

        return obj;
    }

}

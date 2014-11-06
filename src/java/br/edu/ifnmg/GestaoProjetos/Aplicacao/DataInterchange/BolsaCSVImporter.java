/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Bolsa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author petronio
 */
@Stateless
public class BolsaCSVImporter extends CSVImporter<Bolsa> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");

    @EJB
    AgenciaFinanciadoraRepositorio daoAgencia;

    @EJB
    EditalRepositorio daoEdital;

    @EJB
    ModalidadeRepositorio daoModalidade;

    @EJB
    AlunoRepositorio daoAluno;

    @Override
    protected Bolsa gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Bolsa obj = new Bolsa();

        if (cabecalho.containsKey("AgenciaFinanciadora")) {
            AgenciaFinanciadora tmp = daoAgencia.Abrir(colunas[cabecalho.get("AgenciaFinanciadora")]);
            obj.setAgenciaFinanciadora(tmp);
        }

        if (cabecalho.containsKey("DataInicio")) {
            try {
                obj.setDataInicio(df.parse(colunas[cabecalho.get("DataInicio")]));
            } catch (ParseException ex) {
                Logger.getLogger(BolsaCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (cabecalho.containsKey("DataTermino")) {
            try {
                obj.setDataTermino(df.parse(colunas[cabecalho.get("DataTermino")]));
            } catch (ParseException ex) {
                Logger.getLogger(BolsaCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
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
        if (cabecalho.containsKey("Orientando")) {
            Aluno a = daoAluno.AbrirPorCPF(colunas[cabecalho.get("Orientando")]);
            obj.setOrientando(a);

        }

        return obj;
    }

}

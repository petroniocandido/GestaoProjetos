/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.AreaConhecimento;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ejb.Stateless;

/**
 *
 * @author petronio
 */
@Stateless
public class AreaConhecimentoCSVImporter extends CSVImporter<AreaConhecimento> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");

    @Override
    protected AreaConhecimento gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        try {
            AreaConhecimento obj = new AreaConhecimento();
            obj.setNome(colunas[cabecalho.get("nome")]);
            obj.setNumeroCNPQ(colunas[cabecalho.get("numeroCNPQ")]);
            return obj;
        } catch (Exception ex) {
            
            return null;
        }
    }

}

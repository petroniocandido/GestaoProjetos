/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaTipo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class OrientadorCSVImporter extends CSVImporter<Orientador> {

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected Orientador gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Orientador obj = new Orientador();
        try {
            obj.setNome(colunas[cabecalho.get("Nome")]);
            if (cabecalho.containsKey("Cpf")) {
                obj.setCpf(colunas[cabecalho.get("Cpf")]);
            }
            if (cabecalho.containsKey("Email")) {
                obj.setEmail(colunas[cabecalho.get("Email")]);
            }
            if (cabecalho.containsKey("Telefone")) {
                obj.setTelefone(colunas[cabecalho.get("Telefone")]);
            }
            if (cabecalho.containsKey("Lattes")) {
                obj.setLattes(colunas[cabecalho.get("Lattes")]);
            }

            if (cabecalho.containsKey("DataNascimento")) {
                try {
                    obj.setDataNascimento(df.parse(colunas[cabecalho.get("DataNascimento")]));
                } catch (ParseException ex) {
                    Logger.getLogger(OrientadorCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                obj.setTipo(PessoaTipo.valueOf(colunas[cabecalho.get("Tipo")]));
            } catch (Exception ex) {
                Logger.getLogger(OrientadorCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            return null;
        }
        return obj;
    }

}

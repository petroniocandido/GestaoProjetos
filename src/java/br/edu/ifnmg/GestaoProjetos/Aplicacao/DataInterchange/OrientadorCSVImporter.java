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

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");
    
    @Override
    protected Orientador gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Orientador obj = new Orientador();
        obj.setNome(colunas[cabecalho.get("nome")]);
        if(cabecalho.containsKey("cpf"))
            obj.setCpf(colunas[cabecalho.get("cpf")]);
        if(cabecalho.containsKey("email"))
            obj.setEmail(colunas[cabecalho.get("email")]);
        if(cabecalho.containsKey("telefone"))
            obj.setTelefone(colunas[cabecalho.get("telefone")]);
        
        try {
            obj.setDataNascimento(df.parse(colunas[cabecalho.get("dataNascimento")]));
        } catch (ParseException ex) {
            Logger.getLogger(OrientadorCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            obj.setTipo(PessoaTipo.valueOf(colunas[cabecalho.get("tipo")]));
        } catch (Exception ex) {
            Logger.getLogger(OrientadorCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
}

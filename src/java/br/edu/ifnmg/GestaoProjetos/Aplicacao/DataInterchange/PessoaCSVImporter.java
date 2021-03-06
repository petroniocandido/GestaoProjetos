/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.DomainModel.PessoaTipo;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author petronio
 */
@Stateless
public class PessoaCSVImporter extends CSVImporter<PessoaProjeto> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");
    
    @Override
    protected PessoaProjeto gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        PessoaProjeto obj = new PessoaProjeto();
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
            Logger.getLogger(PessoaCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            obj.setTipo(PessoaTipo.valueOf(colunas[cabecalho.get("tipo")]));
        } catch (Exception ex) {
            Logger.getLogger(PessoaCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
}

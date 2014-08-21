/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
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
public class OrientandoCSVImporter extends CSVImporter<Aluno> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");
    
    @Override
    protected Aluno gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Aluno obj = new Aluno();
        obj.setNome(colunas[cabecalho.get("nome")]);
        if(cabecalho.containsKey("matricula"))
            obj.setMatricula(colunas[cabecalho.get("matricula")]);
        if(cabecalho.containsKey("cpf"))
            obj.setCpf(colunas[cabecalho.get("cpf")]);
        if(cabecalho.containsKey("email"))
            obj.setEmail(colunas[cabecalho.get("email")]);
        if(cabecalho.containsKey("telefone"))
            obj.setTelefone(colunas[cabecalho.get("telefone")]);
        if(cabecalho.containsKey("banco"))
            obj.setBanco(colunas[cabecalho.get("banco")]);
        if(cabecalho.containsKey("agencia"))
            obj.setAgencia(colunas[cabecalho.get("agencia")]);
        if(cabecalho.containsKey("conta"))
            obj.setContaBancaria(colunas[cabecalho.get("conta")]);
        
        try {
            obj.setDataNascimento(df.parse(colunas[cabecalho.get("dataNascimento")]));
        } catch (ParseException ex) {
            Logger.getLogger(OrientandoCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            obj.setTipo(PessoaTipo.valueOf(colunas[cabecalho.get("tipo")]));
        } catch (Exception ex) {
            Logger.getLogger(OrientandoCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
}

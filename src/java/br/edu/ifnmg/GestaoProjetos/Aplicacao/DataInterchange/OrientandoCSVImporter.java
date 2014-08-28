/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVImporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Endereco;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaTipo;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
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
public class OrientandoCSVImporter extends CSVImporter<Aluno> {

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    @EJB
    CampusRepositorio daoCampus;

    @Override
    protected Aluno gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Aluno obj = new Aluno();
        try {            
            
            obj.setNome(colunas[cabecalho.get("Nome")]);
            if (cabecalho.containsKey("Matricula")) {
                obj.setMatricula(colunas[cabecalho.get("Matricula")]);
            }
            if (cabecalho.containsKey("Cpf")) {
                obj.setCpf(colunas[cabecalho.get("Cpf")]);
            }
            if (cabecalho.containsKey("Email")) {
                obj.setEmail(colunas[cabecalho.get("Email")]);
            }
            if (cabecalho.containsKey("Telefone")) {
                obj.setTelefone(colunas[cabecalho.get("Telefone")]);
            }
            if (cabecalho.containsKey("Campus")) {
                Campus c = daoCampus.Abrir(colunas[cabecalho.get("Email")]);
                obj.setCampus(c);
            }
            if (cabecalho.containsKey("Banco")) {
                obj.setBanco(colunas[cabecalho.get("Banco")]);
            }
            if (cabecalho.containsKey("Agencia")) {
                obj.setAgencia(colunas[cabecalho.get("Agencia")]);
            }
            if (cabecalho.containsKey("Conta")) {
                obj.setContaBancaria(colunas[cabecalho.get("Conta")]);
            }
            if (cabecalho.containsKey("Endereco")) {
                Endereco end = new Endereco();
                end.setRua(colunas[cabecalho.get("Endereco")]);
                obj.addEndereco(end);
            }
            if (cabecalho.containsKey("DataNascimento")) {
                try {
                    obj.setDataNascimento(df.parse(colunas[cabecalho.get("DataNascimento")]));
                } catch (ParseException ex) {
                    Logger.getLogger(OrientandoCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                obj.setTipo(PessoaTipo.valueOf(colunas[cabecalho.get("tipo")]));
            } catch (Exception ex) {
                Logger.getLogger(OrientandoCSVImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (Exception ex){
            
        }
       return obj;
    }

}

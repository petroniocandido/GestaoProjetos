/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.GestaoProjetos.Aplicacao.DataInterchange;

import br.edu.ifnmg.GestaoProjetos.Aplicacao.CSVExporter;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author petronio
 */
public class PessoaCSVExporter extends CSVExporter<PessoaProjeto> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");
    
    @Override
    protected StringBuilder gerarLinha(PessoaProjeto obj) {
        return new StringBuilder(obj.getNome()).append(";")
                .append(obj.getCpf() != null ? obj.getCpf() : "").append(";")
                .append(obj.getTelefone() != null ? obj.getTelefone(): "").append(";")
                .append(obj.getEmail() != null ? obj.getEmail(): "").append(";")
                .append(obj.getDataNascimento() != null ? df.format(obj.getDataNascimento()) : "").append(";")
                .append(obj.getPerfil() != null ? obj.getPerfil().getId().toString() : "").append(";")
                .append(obj.getTipo() != null ? obj.getTipo() : "").append(";");
    }

    @Override
    protected StringBuilder gerarCabecalho(PessoaProjeto obj) {
        return new StringBuilder("nome;cpf;telefone;email;dataNascimento;perfil;tipo;");
        
    }
    
}

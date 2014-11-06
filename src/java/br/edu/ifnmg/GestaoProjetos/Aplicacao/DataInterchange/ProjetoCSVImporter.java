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
import br.edu.ifnmg.GestaoProjetos.DomainModel.Campus;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Edital;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Modalidade;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Projeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AgenciaFinanciadoraRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AreaConhecimentoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.CampusRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.EditalRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.ModalidadeRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class ProjetoCSVImporter extends CSVImporter<Projeto> {

    DateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");

    @EJB
    CampusRepositorio daoCampus;

    @EJB
    OrientadorRepositorio daoCoordenador;

    @EJB
    AreaConhecimentoRepositorio daoAreaConhecimento;

    @Override
    protected Projeto gerarObjeto(String linha) {
        String colunas[] = linha.split(";");
        Projeto obj = new Projeto();
        obj.setTitulo(colunas[cabecalho.get("Titulo")]);

        
        if (cabecalho.containsKey("PalavrasChave")) {
            obj.setPalavrasChave(colunas[cabecalho.get("PalavrasChave")]);
        }

        if (cabecalho.containsKey("Campus")) {
            Campus tmp = daoCampus.Abrir(colunas[cabecalho.get("Campus")]);
            obj.setCampus(tmp);
        }

        if (cabecalho.containsKey("Coordenador")) {
            Orientador tmp = daoCoordenador.AbrirPorCPF(colunas[cabecalho.get("Coordenador")]);
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
        if (cabecalho.containsKey("NumeroCadastro")) {
            int num = Integer.parseInt(colunas[cabecalho.get("NumeroCadastro")]);
            obj.setNumeroCadastro(num);
        }
        if (cabecalho.containsKey("ValorFinanciamento")) {
            BigDecimal tmp = new BigDecimal(colunas[cabecalho.get("ValorFinanciamento")]);
            obj.setValorFinanciamento(tmp);
        }
        
        if (cabecalho.containsKey("AreaConhecimento")) {
            String cods[] = colunas[cabecalho.get("AreaConhecimento")].split(",");
            for(String cod : cods){
                AreaConhecimento a = daoAreaConhecimento.Abrir(cod);
                obj.addAreaConhecimento(a);
            }
        }

        return obj;
    }

}

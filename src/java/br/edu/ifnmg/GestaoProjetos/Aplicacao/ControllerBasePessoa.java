/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Aplicacao;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Email;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Endereco;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Estados;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Pessoa;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.HashService;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Telefone;
import javax.inject.Inject;

/**
 *
 * @author petronio
 * @param <T>
 */
public abstract class ControllerBasePessoa<T extends Pessoa> extends ControllerBaseEntidade<T> {

    Email email;
    Telefone telefone;
    Endereco endereco;

    @Inject
    HashService hash;

    String senha1, senha2;
    
    Estados[] estados;

    /**
     * Creates a new instance of AlunoController
     */
    public ControllerBasePessoa() {
        email = new Email();
        telefone = new Telefone();
        endereco = new Endereco();
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public void addTelefone() {
        //entidade = dao.Refresh(entidade.getId());
        getEntidade().addTelefone(telefone);
        getRepositorio().Salvar(entidade);
        telefone = new Telefone();
    }

    public void addEndereco() {
        getEntidade().addEndereco(endereco);
        getRepositorio().Salvar(entidade);
        endereco = new Endereco();
    }

    public void addEmail() {
        getEntidade().addEmail(email);
        getRepositorio().Salvar(entidade);
        email = new Email();
    }

    public void removeEndereco() {
        getEntidade().removeEndereco(endereco);
        getRepositorio().Salvar(entidade);
        endereco = new Endereco();
    }

    public void removeTelefone() {
        getEntidade().removeTelefone(telefone);
        getRepositorio().Salvar(entidade);
        telefone = new Telefone();
    }

    public void removeEmail() {
        getEntidade().removeEmail(email);
        getRepositorio().Salvar(entidade);
        email = new Email();
    }

    @Override
    public void salvar() {

        if (senha1 != null && senha1.length() != 0) {

            if (senha1.equals(senha2)) {
                entidade.setSenha(hash.getMD5(senha1));
            } else {
                Mensagem("Erro", "As senhas não conferem!");
                return;
            }
        }

        SalvarEntidade();

        // atualiza a listagem
        filtrar();
    }

    public Estados[] getEstados() {
        if(estados == null)
            estados = Estados.values();
        return estados;
    }
    
    
}

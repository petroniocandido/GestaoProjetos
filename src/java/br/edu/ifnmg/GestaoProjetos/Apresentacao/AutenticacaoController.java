/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Apresentacao;

import br.edu.ifnmg.DomainModel.Services.AutorizacaoService;
import br.edu.ifnmg.DomainModel.Services.HashService;
import br.edu.ifnmg.DomainModel.Services.PerfilRepositorio;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.ControllerBase;
import br.edu.ifnmg.GestaoProjetos.Aplicacao.ValidadorCPF;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Aluno;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Orientador;
import br.edu.ifnmg.GestaoProjetos.DomainModel.PessoaProjeto;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.AlunoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.OrientadorRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PessoaProjetoRepositorio;
import br.edu.ifnmg.GestaoProjetos.DomainModel.UsuarioTipo;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author petronio
 */
@Named(value = "autenticacaoController")
@RequestScoped
public class AutenticacaoController
        extends ControllerBase
        implements Serializable {

    /**
     * Creates a new instance of AutenticacaoController
     */
    public AutenticacaoController() {

    }
    
    UsuarioTipo tipo;

    @EJB
    PessoaProjetoRepositorio dao;
    
    @EJB
    OrientadorRepositorio daoO;
    
    @EJB
    AlunoRepositorio daoA;
    
    @EJB
    HashService hash;
    @EJB
    PerfilRepositorio perfilDAO;
    @Inject
    AutorizacaoService autorizacao;

    private String login, senha, senhaconferencia;
    PessoaProjeto usuario;

    public void validar() {
        if (autenticacao.login(login, senha)) {
            AppendLog("Login");
            Redirect(autenticacao.getUsuarioCorrente().getPerfil().getHome().getUri());
        } else {
            MensagemErro("Falha", "Login ou senha não correspondem");
        }

    }

    public void logout() throws IOException {

        AppendLog("Logout");

        setUsuario(null);

        autenticacao.logout();

        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("../login.xhtml");

    }

    public String novo() {
        setUsuario(null);
        return "cadastrar.xhtml";
    }

    public void cadastrar() {
        getUsuario().setPerfil(perfilDAO.getPadrao());
        usuario.setSenha(java.util.UUID.randomUUID().toString());
        if (dao.Salvar(usuario)) {
            autenticacao.redefinirSenha(usuario.getEmail());
            AppendLog("Cadastro do usuário " + usuario.getEmail());
        } else {
            AppendLog("Erro ao cadastrar usuário: " + dao.getErro().toString());
        }
    }
    
    public void cadastrarAluno() {
        Aluno a = getAluno();
        a.setPerfil(perfilDAO.getPadrao());
        a.setSenha(java.util.UUID.randomUUID().toString());
        if (daoA.Salvar(a)) {
            autenticacao.redefinirSenha(a.getEmail());
            AppendLog("Cadastro do usuário " + a.getEmail());
        } else {
            AppendLog("Erro ao cadastrar usuário: " + daoA.getErro().toString());
        }
    }
    
    public void cadastrarOrientador() {
        Orientador o = getOrientador();
        o.setPerfil(perfilDAO.getPadrao());
        o.setSenha(java.util.UUID.randomUUID().toString());
        if (daoO.Salvar(o)) {
            autenticacao.redefinirSenha(o.getEmail());
            AppendLog("Cadastro do usuário " + o.getEmail());
        } else {
            AppendLog("Erro ao cadastrar usuário: " + daoO.getErro().toString());
        }
    }

    public void salvar() {
        if (senha != null && !senha.isEmpty()) {
            if (senha.equals(senhaconferencia)) {
                getUsuario().setSenha(hash.getMD5(senha));
            } else {
                MensagemErro("Senhas", "Senhas não conferem!");
                return;
            }
        }
        if (dao.Salvar(usuario)) {
            AppendLog("Cadastro do usuário " + usuario.getEmail());
        } else {
            AppendLog("Erro ao cadastrar usuário: " + dao.getErro().toString());
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaconferencia() {
        return senhaconferencia;
    }

    public void setSenhaconferencia(String senhaconferencia) {
        this.senhaconferencia = senhaconferencia;
    }

    public PessoaProjeto getUsuario() {
        if (usuario == null) {
            usuario = (PessoaProjeto) getSessao("usuarioAutenticado", dao);
            if (usuario == null) {
                usuario = new PessoaProjeto();
            }
        }
        return usuario;
    }
    
    public Aluno getAluno() {
        if (usuario == null) {
            usuario = (PessoaProjeto) getSessao("usuarioAutenticado", daoA);
            if (usuario == null) {
                usuario = new Aluno();
            }
        }
        return (Aluno)usuario;
    }
    
    public Orientador getOrientador() {
        if (usuario == null) {
            usuario = (PessoaProjeto) getSessao("usuarioAutenticado", daoO);
            if (usuario == null) {
                usuario = new Orientador();
            }
        }
        return (Orientador)usuario;
    }

    public void setUsuario(PessoaProjeto usuario) {
        this.usuario = usuario;
        setSessao("usuarioAutenticado", usuario);
    }

    public boolean autorizacao(String url) {
        return autorizacao.possuiPermissao(url);
    }

    public void novasenha() {
        if (autenticacao.redefinirSenha(login)) {
            Mensagem("Sucesso!", "Foi enviado para o seu e-mail uma nova senha! ");
        } else {
            MensagemErro("Falha!", "Houve um problema ao enviar o e-mail com a nova senha! "
                    + "Consulte o administrador do sistema ou tente novamente em alguns instantes.");
        }
    }

    public void idleListener() {
        autenticacao.logout();
    }

    public void validaCPF(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (getUsuarioCorrente() != null) {
            return;
        }

        PessoaProjeto tmp = dao.AbrirPorCPF(value.toString());

        if (tmp != null) {
            FacesMessage msg
                    = new FacesMessage("CPF já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (!ValidadorCPF.validaCPF(value.toString())) {
            FacesMessage msg
                    = new FacesMessage("CPF Inválido!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validaEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (getUsuarioCorrente() != null) {
            return;
        }

        PessoaProjeto tmp = dao.Abrir(value.toString());

        if (tmp != null) {
            FacesMessage msg
                    = new FacesMessage("E-mail já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioTipo tipo) {
        this.tipo = tipo;
    }
    
    public UsuarioTipo[] getTipos() {
        return UsuarioTipo.values();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Isla Guedes
 */
@Entity
@Table(name="alunos")
@Cacheable(true)
public class Aluno extends Pessoa implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;

    //Documentos   
    private String tituloEleitoral;

    private String secaoEleitoral;

    private String zonaEleitoral;

    private String situacaoMilitar;

    private String certidaoMilitar;

    private boolean trabalhoStatus;

    //Dados dos pais    
    private String nomePai;

    private String rgPai;

    private String orgaoExpedidorPai;

    @Column(length = 14)
    private String cpfPai;

    private String nomeMae;

    private String rgMae;

    private String orgaoExpedidorMae;

    @Column(length = 14)
    private String cpfMae;

    //Dados da Conta
    private String banco;

    private String contaBancaria;

    private String agencia;

    @ManyToOne
    private Curso curso;

    private String localTrabalho;

    public Aluno() {

        super();  
    }

    @Override
    public void setId(Long id) {
        this.id = id; 
    }

    @Override
    public Long getId() {
        return this.id; 
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTituloEleitoral() {
        return tituloEleitoral;
    }

    public void setTituloEleitoral(String tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }

    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(String secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public String getSituacaoMilitar() {
        return situacaoMilitar;
    }

    public void setSituacaoMilitar(String situacaoMilitar) {
        this.situacaoMilitar = situacaoMilitar;
    }

    public String getCertidaoMilitar() {
        return certidaoMilitar;
    }

    public void setCertidaoMilitar(String certidaoMilitar) {
        this.certidaoMilitar = certidaoMilitar;
    }

    public boolean isTrabalhoStatus() {
        return trabalhoStatus;
    }

    public void setTrabalhoStatus(boolean trabalhoStatus) {
        this.trabalhoStatus = trabalhoStatus;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getRgPai() {
        return rgPai;
    }

    public void setRgPai(String rgPai) {
        this.rgPai = rgPai;
    }

    public String getOrgaoExpedidorPai() {
        return orgaoExpedidorPai;
    }

    public void setOrgaoExpedidorPai(String orgaoExpedidorPai) {
        this.orgaoExpedidorPai = orgaoExpedidorPai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getRgMae() {
        return rgMae;
    }

    public void setRgMae(String rgMae) {
        this.rgMae = rgMae;
    }

    public String getOrgaoExpedidorMae() {
        return orgaoExpedidorMae;
    }

    public void setOrgaoExpedidorMae(String orgaoExpedidorMae) {
        this.orgaoExpedidorMae = orgaoExpedidorMae;
    }

    public String getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(String cpfPai) {
        this.cpfPai = cpfPai;
    }

    public String getCpfMae() {
        return cpfMae;
    }

    public void setCpfMae(String cpfMae) {
        this.cpfMae = cpfMae;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
    

    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa criador;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa ultimoAlterador;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimaAlteracao;

    @Version
    private Long versao;

    @Override
    public Pessoa getCriador() {
        return criador;
    }

    @Override
    public void setCriador(Pessoa criador) {
        this.criador = criador;
    }

    @Override
    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public Pessoa getUltimoAlterador() {
        return ultimoAlterador;
    }

    @Override
    public void setUltimoAlterador(Pessoa ultimoAlterador) {
        this.ultimoAlterador = ultimoAlterador;
    }

    @Override
    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    @Override
    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    @Override
    public Long getVersao() {
        return versao;
    }

    @Override
    public void setVersao(Long versao) {
        this.versao = versao;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
@Table(name="documentos")
@Cacheable(false)
public class Documento implements Serializable, Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    @ManyToOne
    private Pessoa pessoa;
    
    @ManyToOne
    private Pessoa funcionarioRecebedor;
    
    @ManyToOne
    private DocumentoTipo tipoDocumento;
    
    @Temporal(javax.persistence.TemporalType.DATE) 
    private Date dataPrevista;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEfetiva;
    
    @ManyToOne
    private Arquivo arquivo;
    
    @Enumerated
    private DocumentoSituacao situacao;
    
    public Status getStatus() {
        Date hoje = new Date();
        
        if(hoje.after(dataPrevista) && dataEfetiva == null && funcionarioRecebedor == null)
            return Status.Pendente;
        
        return Status.Regular;
    }
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getFuncionarioRecebedor() {
        return funcionarioRecebedor;
    }

    public void setFuncionarioRecebedor(Pessoa funcionarioRecebedor) {
        this.funcionarioRecebedor = funcionarioRecebedor;
    }

    public DocumentoTipo getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(DocumentoTipo tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataEfetiva() {
        return dataEfetiva;
    }

    public void setDataEfetiva(Date dataEfetiva) {
        this.dataEfetiva = dataEfetiva;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DocumentoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(DocumentoSituacao situacao) {
        this.situacao = situacao;
    }
        

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.pessoa);
        hash = 97 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 97 * hash + Objects.hashCode(this.dataPrevista);
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
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
       if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        if (!Objects.equals(this.dataPrevista, other.dataPrevista)) {
            return false;
        }
        return true;
    }
    
    
    

    @Override
    public String toString() {
        return tipoDocumento.getSigla();
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

    public void setVersao(Long versao) {
        this.versao = versao;
    }
    
}

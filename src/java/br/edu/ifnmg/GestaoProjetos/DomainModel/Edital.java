/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Isla Guedes
 */
@Entity
@Table(name = "editais")
@Cacheable(true)
public class Edital implements Entidade, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int numero;

    @Column
    private int numeroBolsas;

    @ManyToMany
    private List<AgenciaFinanciadora> agenciasFinanciadoras;

    @OneToOne
    private Campus campus;

    @ManyToMany
    private List<Modalidade> modalidades;

    @Column(nullable = false, unique = true)
    private String sigla;

    @ManyToMany
    private List<Arquivo> arquivos;

    @ManyToMany
    private List<DocumentoTipo> documentosObrigatorios;

    @OrderBy("data")
    @ElementCollection
    @CollectionTable(
            name = "editais_cronogramas",
            joinColumns = @JoinColumn(name = "edital")
    )
    @MapKeyTemporal(TemporalType.TIMESTAMP)
    private Map<Date, String> cronograma;

    public Edital() {
        arquivos = new ArrayList<>();
        documentosObrigatorios = new ArrayList<>();
        cronograma = new HashMap<>();

    }

    public void add(Arquivo t) {
        if (!arquivos.contains(t)) {
            arquivos.add(t);
        }

    }

    public void remove(Arquivo t) {
        if (arquivos.contains(t)) {
            arquivos.remove(t);
        }
    }

    public void addCronograma(String evt, Date data) {
        if (!cronograma.containsKey(data)) {
            cronograma.put(data, evt);
        }
    }

    public void removeCronograma(Date evt) {
        if (cronograma.containsKey(evt)) {
            cronograma.remove(evt);
        }
    }

    public void add(DocumentoTipo t) {
        if (!documentosObrigatorios.contains(t)) {
            documentosObrigatorios.add(t);
        }

    }

    public void remove(DocumentoTipo t) {
        if (documentosObrigatorios.contains(t)) {
            documentosObrigatorios.remove(t);
        }
    }

    public void add(Modalidade t) {
        if (!modalidades.contains(t)) {
            modalidades.add(t);
        }

    }

    public void remove(Modalidade t) {
        if (modalidades.contains(t)) {
            modalidades.remove(t);
        }
    }

    public void add(AgenciaFinanciadora t) {
        if (!agenciasFinanciadoras.contains(t)) {
            agenciasFinanciadoras.add(t);
        }

    }

    public void remove(AgenciaFinanciadora t) {
        if (agenciasFinanciadoras.contains(t)) {
            agenciasFinanciadoras.remove(t);
        }
    }

    //GETTER E SETTER
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroBolsas() {
        return numeroBolsas;
    }

    public void setNumeroBolsas(int numeroBolsas) {
        this.numeroBolsas = numeroBolsas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<DocumentoTipo> getDocumentosObrigatorios() {
        return documentosObrigatorios;
    }

    public void setDocumentosObrigatorios(List<DocumentoTipo> documentosObrigatorios) {
        this.documentosObrigatorios = documentosObrigatorios;
    }

    public Map<Date, String> getCronograma() {
        return cronograma;
    }

    public void setCronograma(Map<Date, String> cronograma) {
        this.cronograma = cronograma;
    }

    public List<AgenciaFinanciadora> getAgenciasFinanciadoras() {
        return agenciasFinanciadoras;
    }

    public void setAgenciasFinanciadoras(List<AgenciaFinanciadora> agenciaFinanciadoras) {
        this.agenciasFinanciadoras = agenciaFinanciadoras;
    }

    public List<Modalidade> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edital)) {
            return false;
        }
        Edital other = (Edital) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sigla;
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

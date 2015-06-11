/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.DomainModel;

import br.edu.ifnmg.DomainModel.Entidade;
import br.edu.ifnmg.DomainModel.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Isla Guedes
 */
@Entity
@Table(name="orientadores")
@Cacheable(true)
public class Orientador extends PessoaProjeto implements Entidade, Serializable{
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private int matriculaSiape;     
    
    private String localPermanencia;
    
    @ManyToMany
    private List<AreaConhecimento> areaConhecimento;
    
   //Dados de Formação     
    
    private String formacaoUniversitaria;
        
    private String tituloAcademico;    
    
    private String lattes;
    
   //Dados de Solicitação
    @OneToMany(mappedBy = "coordenador", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Projeto> projetos;
    
  
    public Orientador() {
        super();
        this.areaConhecimento = new ArrayList<>();
        setUsuarioTipo(UsuarioTipo.Orientador);
    }

    public void addAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento == null) {
            areaConhecimento = new ArrayList<>();
        }
        if(!areaConhecimento.contains(a)){
            areaConhecimento.add(a);
        }
        
    }
    
    public void removeAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento.contains(a)){
            areaConhecimento.remove(a);
        }
    }
    
    //GETTER E SETTER
    
    @Override
    public void setId(Long id) {
        this.id = id; 
    }

    @Override
    public Long getId() {
        return this.id; 
    }

    public int getMatriculaSiape() {
        return matriculaSiape;
    }

    public void setMatriculaSiape(int matriculaSiape) {
        this.matriculaSiape = matriculaSiape;
    }

    public String getLocalPermanencia() {
        return localPermanencia;
    }

    public void setLocalPermanencia(String localPermanencia) {
        this.localPermanencia = localPermanencia;
    }

    public List<AreaConhecimento> getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(List<AreaConhecimento> areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public String getFormacaoUniversitaria() {
        return formacaoUniversitaria;
    }

    public void setFormacaoUniversitaria(String formacaoUniversitaria) {
        this.formacaoUniversitaria = formacaoUniversitaria;
    }

    public String getTituloAcademico() {
        return tituloAcademico;
    }

    public void setTituloAcademico(String tituloAcademico) {
        this.tituloAcademico = tituloAcademico;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + this.matriculaSiape;
        hash = 43 * hash + Objects.hashCode(this.lattes);
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
        final Orientador other = (Orientador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.matriculaSiape != other.matriculaSiape) {
            return false;
        }
        if (!Objects.equals(this.lattes, other.lattes)) {
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
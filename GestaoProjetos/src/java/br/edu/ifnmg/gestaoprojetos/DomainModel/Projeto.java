/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Projeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //identificação do projeto
    @Column(nullable=false)
    private String titulo; 
    
    private int numeroCadastro; 
    
    //informações adicionais
    @ManyToMany //VERIFICAR
    private List<AreaConhecimento> areaConhecimento;
    
    private boolean grupoPesquisa;
    
    @Column(nullable=false)
    private String nomegrupoPesquisa;
    
    @ManyToOne  //VERIFICAR
    private Campus campus;
    
    //Resumo do projeto
    @Column(nullable=false, length= 2000)
    private String resumo;
    
    @Column(nullable=false, length=500)
    private String palavrasChave;
    
    //Duração do projeto
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataTermino;
    
    //Coordenação do projeto
    @ManyToOne 
    @Column(nullable=false)
     private Orientador coordenador;
     
     @Column(nullable=false)
     private String setorCoordenador;
     
     //Financiamento/Iniciação científica
     private boolean projetoFinanciamento;
     
     @Column(nullable=false)
     private BigInteger valorFinanciamento;
     
     @Temporal(javax.persistence.TemporalType.DATE)
     @Column(nullable=false)
     private Date dataFinanciamento;
     
     private boolean bolsaIniciacaoCientifica;
     
     @Column(nullable=false)
     private int numeroBolsas;
     
     @ManyToOne     
     private AgenciaFinanciadora agenciaFinanciadora; 
     
     //Convênio/Gestão
     private boolean projetoConvenio;
     
     @Column(nullable=false)
     private String nomeConvenio;
     
     private boolean projetoFundacao;
     
     @Column(nullable=false)
     private String nomeProjetoFundacao;
     
     private boolean projetoMulticampi;
     
     //Identificação dos Participantes dos Projetos
     @ManyToMany
     private List<Usuario> participantesProjeto;   
     
     
     //GETTER E SETTER
     
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Projeto[ id=" + id + " ]";
    }
    
}

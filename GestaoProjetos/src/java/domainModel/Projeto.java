/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private Long idProjeto;
    
    //identificação do projeto
    @Column(nullable=false)
    private String tituloProjeto; 
    
    private int numeroCadastroProjeto; //VER SER É NECESSÁRIO
    
    //informações adicionais
    @OneToMany //VERIFICAR
    private List<AreaConhecimento> areaConhecimento;
    
    private boolean grupoPesquisa;
    
    @Column(nullable=false)
    private String nomegrupoPesquisa;
    
    @ManyToOne  //VERIFICAR
    private Campus campus;
    
    //Resumo do projeto
    @Column(nullable=false)
    private String resumo;
    
    @Column(nullable=false)
    private String palavrasChaveProjeto;
    
    //Duração do projeto
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date dataTermino;
    
    //Coordenação do projeto
     @Column(nullable=false)
     private String nomeCoordenador;
     
     @Column(nullable=false)
     private String setorCoordenador;
     
     //Financiamento/Iniciação científica
     private boolean projetoFinanciamento;
     
     @Column(nullable=false)
     private double valorFinanciamento;
     
     @Temporal(javax.persistence.TemporalType.DATE)
     @Column(nullable=false)
     private Date dataFinanciamento;
     
     private boolean bolsaIniciacaoCientifica;
     
     @Column(nullable=false)
     private int numeroBolsas;
     
     @OneToMany     
     private List<AgenciaFinanciadora> agenciaFinanciadora; 
     
     //Convênio/Gestão
     private boolean projetoConvenio;
     
     @Column(nullable=false)
     private String nomeConvenio;
     
     private boolean projetoFundacao;
     
     @Column(nullable=false)
     private String nomeProjetoFundacao;
     
     private boolean projetoMulticampi;
     
     //Identificação dos Participantes dos Projetos
     @OneToMany
     private List<Usuario> participantesProjeto;   
     
     
     //GETTER E SETTER
     
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjeto != null ? idProjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.idProjeto == null && other.idProjeto != null) || (this.idProjeto != null && !this.idProjeto.equals(other.idProjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domainModel.Projeto[ id=" + idProjeto + " ]";
    }
    
}

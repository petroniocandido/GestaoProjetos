/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.GestaoProjetos.Infraestrutura.Dados;

import br.edu.ifnmg.GestaoProjetos.DomainModel.Perfil;
import br.edu.ifnmg.GestaoProjetos.DomainModel.Servicos.PerfilRepositorio;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author petronio
 */
@Singleton
public class PerfilDAO
        extends DAOGenerico<Perfil>
        implements PerfilRepositorio {

    public PerfilDAO() {
        super(Perfil.class);
    }

    @Override
    public Perfil Abrir(String nome) {
        return IgualA("nome", nome).Abrir();
    }

    @Override
    public boolean Salvar(Perfil obj) {

        if (super.Salvar(obj)) {
            if (obj.isPadrao()) {
                return DiferenteDe("id",obj.getId()).Setar("padrao", false).Atualiza();
            }
            return true;
        }
        else return false;
    }

    @Override
    public Perfil getPadrao() {
        return IgualA("padrao", true).Abrir();

    }

    @Override
    public List<Perfil> Buscar(Perfil filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .Like("descricao", filtro.getDescricao())
                .IgualA("padrao", filtro.isPadrao())
                .Buscar();

    }

}

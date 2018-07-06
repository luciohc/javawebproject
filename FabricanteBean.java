/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.bean;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.Facesutil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Lucio Henrique
 */
@ManagedBean
@ViewScoped
public class FabricanteBean {

    private Fabricante fabricanteCadastro;
    private List<Fabricante> listaFabricantes;
    private List<Fabricante> listaFabricantesFiltrados;
    
    private String acao;
    private Long codigo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Fabricante getFabricanteCadastro() {
        
        return fabricanteCadastro;
    }

    public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
        this.fabricanteCadastro = fabricanteCadastro;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
       

    public void novo() {
        fabricanteCadastro = new Fabricante();
    }

    public void salvar() {
        try {
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricanteDAO.salvar(fabricanteCadastro);
            fabricanteCadastro = new Fabricante();
            Facesutil.adicionarMsgInfo("Fabricante salvo com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar incluir um fabricante: " + ex.getMessage());
        }

    }

    public void carregar() {
        try {
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            listaFabricantes = fabricanteDAO.listar();

        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar listar os fabricantes:" + ex.getMessage());

        }
    }

    public void carregarCadastro() {
        try {
            
            
            
            if (codigo != null) {
                
                FabricanteDAO fabricanteDAO = new FabricanteDAO();
                fabricanteDAO.buscarPorCodigo(codigo);
            }else{
                fabricanteCadastro = new Fabricante();
            }
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar obter os dados do fabricante:" + ex.getMessage());
        }

    }

    public void excluir() {
        try {
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricanteDAO.excluir(fabricanteCadastro);
            Facesutil.adicionarMsgInfo("Fabricante removido com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar remover o fabricante: " + ex.getMessage());
        }

    }

    public void editar() {
        try {
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricanteDAO.editar(fabricanteCadastro);
            
            Facesutil.adicionarMsgInfo("Fabricante editado com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar editar os dados do fabricante: " + ex.getMessage());
        }

    }

}

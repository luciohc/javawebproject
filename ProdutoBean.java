/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.bean;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;

import java.util.List;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.Facesutil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Lucio Henrique
 */
@ManagedBean
@ViewScoped
public class ProdutoBean {
    private Produto produtoCadastro;
    
    private List<Produto>  listaProdutos;
    private List<Produto>  listaProdutosFiltrados;
    
    private String acao;
    private Long codigo;

    private List<Fabricante> listaFabricantes;
    
    public Produto getProdutoCadastro() {
        return produtoCadastro;
    }

    public void setProdutoCadastro(Produto produtoCadastro) {
        this.produtoCadastro = produtoCadastro;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Produto> getListaProdutosFiltrados() {
        return listaProdutosFiltrados;
    }

    public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
        this.listaProdutosFiltrados = listaProdutosFiltrados;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<Fabricante> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }
    
    public void novo() {
        produtoCadastro = new Produto();
    }

    public void salvar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.salvar(produtoCadastro);
            produtoCadastro = new Produto();
            Facesutil.adicionarMsgInfo("Produto salvo com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar incluir um produto: " + ex.getMessage());
        }

    }

    public void carregar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            listaProdutos = produtoDAO.listar();

        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar listar os fabricantes:" + ex.getMessage());

        }
    }

    public void carregarCadastro() {
        try {        
           if (codigo != null) {
                
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
            }else{
                produtoCadastro = new Produto();
            }
           
           FabricanteDAO fabricanteDAO = new FabricanteDAO();
           listaFabricantes = fabricanteDAO.listar();
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar obter os dados do produto:" + ex.getMessage());
        }

    }

    public void excluir() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.excluir(produtoCadastro);
            Facesutil.adicionarMsgInfo("Produto removido com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar remover o produto: " + ex.getMessage());
        }

    }

    public void editar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.editar(produtoCadastro);
            
            Facesutil.adicionarMsgInfo("Produto editado com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar editar os dados do produto: " + ex.getMessage());
        }

    }
    
}

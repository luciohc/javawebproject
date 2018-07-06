/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.teste;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Lucio Henrique
 */
public class ProdutoDAOTeste {

    @Test
    @Ignore
    public void salvar() {
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        Fabricante fabricante = fabricanteDAO.buscarPorCodigo(4L);

        Produto produto = new Produto();

        produto.setDescricao("DESCRICAOX");
        produto.setPreco(new BigDecimal(23.55D));
        produto.setQuantidade(17);
        produto.setFabricante(fabricante);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.salvar(produto);

    }

    @Test
    @Ignore
    public void buscarPorCodigo() {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = produtoDAO.buscarPorCodigo(2L);

        System.out.println(produto);

    }

    @Test
    @Ignore
    public void listar() {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = produtoDAO.listar();

        System.out.println(produtos);

    }

    @Test
    @Ignore
    public void excluir() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscarPorCodigo(3L);
        produtoDAO.excluir(produto);

    }
    
    @Test
    @Ignore
    public void editar(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        Produto produto = produtoDAO.buscarPorCodigo(2L);
        produto.setDescricao("DESCRICAOB");
        produto.setPreco(new BigDecimal(6.37D));
        produto.setQuantidade(9);
        
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        Fabricante fabricante  = fabricanteDAO.buscarPorCodigo(1L);
        produto.setFabricante(fabricante);
        
        
    }
            
    
    


}

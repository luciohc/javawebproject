/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.bean;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.Facesutil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Lucio Henrique
 */
public class VendaBean {

    private List<Produto> listaProdutos;
    private List<Produto> listaProdutosFiltrados;

    private Venda vendaCadastro;

    private List<Item> listaItens;
    
    @ManagedProperty(value ="#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

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

    public List<Item> getListaItens() {
        if (listaItens == null) {
            listaItens = new ArrayList<>();
        }
        return listaItens;
    }

    public void setListaItens(List<Item> listaItens) {
        this.listaItens = listaItens;
    }

    public Venda getVendaCadastro() {
        if (vendaCadastro == null) {
            vendaCadastro = new Venda();
            vendaCadastro.setValor(new BigDecimal("0.00"));
        }
        return vendaCadastro;
    }

    public void setVendaCadastro(Venda vendaCadastro) {
        this.vendaCadastro = vendaCadastro;
    }

    public AutenticacaoBean getAutenticacaoBean() {
        return autenticacaoBean;
    }

    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }
    
    
    

    public void carregarProdutos() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            listaProdutos = produtoDAO.listar();

        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar listar os fabricantes:" + ex.getMessage());

        }
    }

    public void adicionar(Produto produto) {
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
            Item itemTemp = listaItens.get(pos);
            if (itemTemp.getProduto().equals(produto)) {
                posicaoEncontrada = pos;
            }
        }
        Item item = new Item();
        item.setProduto(produto);

        if (posicaoEncontrada < 0) {
            item.setQuantidade(1);
            item.setValor(produto.getPreco());
            listaItens.add(item);
        } else {
            Item itemTemp = listaItens.get(posicaoEncontrada);
            item.setQuantidade(itemTemp.getQuantidade() + 1);
            item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
            listaItens.set(posicaoEncontrada, item);
        }

        vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));
    }

    public void remover(Item item) {
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
            Item itemTemp = listaItens.get(pos);
            if (itemTemp.getProduto().equals(item.getProduto())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            listaItens.remove(posicaoEncontrada);
            vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
        }
    }

    public void carregarDadosVenda() {
        vendaCadastro.setHorario(new Date());
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());
        vendaCadastro.setFuncionario(funcionario);

    }

    public void salvar() {
        try {
            VendaDAO vendaDAO = new VendaDAO();  
            
            Long codigoVenda = vendaDAO.salvar(vendaCadastro);
            
            Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);
            
            for(Item item : listaItens){
                item.setVenda(vendaFK);
                
                ItemDAO itemDAO = new ItemDAO();
                
                itemDAO.salvar(item);
                
            }

            vendaDAO.salvar(vendaCadastro);

            vendaCadastro = new Venda();
            vendaCadastro.setValor(new BigDecimal("0.00"));
            listaItens = new ArrayList<Item>();
            Facesutil.adicionarMsgInfo("Venda salva com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar salvar a venda:" + ex.getMessage());
        }
    }

}

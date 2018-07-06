/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.teste;

import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Lucio Henrique
 */


public class VendaDAOTeste {
    @Test
    @Ignore
    public void salvar(){
       FuncionarioDAO funcionarioDAO = new FuncionarioDAO();    
       Funcionario funcionario = funcionarioDAO.buscarPorCodigo(7L);
       
       Venda venda  = new Venda();
       venda.setFuncionario(funcionario);
       venda.setHorario(new Date());
       venda.setValor(new BigDecimal(12.34D));
       
       VendaDAO vendaDAO = new VendaDAO();
       vendaDAO.salvar(venda);
       
    }
    
    @Test
    @Ignore
    public void listar(){
        VendaDAO vendaDAO = new VendaDAO();
        
        List<Venda> vendas = vendaDAO.listar();
        System.out.println(vendas);
        
    }
    
    @Test
    @Ignore
    public void buscarPorCodigo() {
        VendaDAO vendaDAO  = new VendaDAO();

        Venda venda = vendaDAO.buscarPorCodigo(3L);
        
        System.out.println(venda);

    }
    
    @Test
    @Ignore
    public void excluir() {
        VendaDAO vendaDAO  = new VendaDAO();
        Venda venda = vendaDAO.buscarPorCodigo(3L);
        vendaDAO.excluir(venda);

    }
    
    @Test
    @Ignore
    public void editar(){
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        Funcionario funcionario = funcionarioDAO.buscarPorCodigo(2L);
        
        VendaDAO venda DAO = new VendaDAO();
        Venda venda = vendaDAO.buscarPorCodigo(3L);
        
       
       venda.setHorario(new Date());
       venda.setValor(new BigDecimal(12.34D));
        venda.setFuncionario(funcionario);
       
       
       vendaDAO.editar(venda);
    }
          
    
    
    
}

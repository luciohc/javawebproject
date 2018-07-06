/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.teste;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Lucio Henrique
 */
public class FuncionarioDAOTest {

    @Test
    public void autenticar() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        Funcionario funcionario = funcionarioDAO.autenticar("874.733.646-34", "q1w2e3r4");
        
        System.out.println("Funcionário" + funcionario);
        
        Assert.assertNotNull(funcionario);      

    }

}

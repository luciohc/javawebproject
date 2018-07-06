/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.bean;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.Facesutil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Lucio Henrique
 */
@ManagedBean
@SessionScoped
public class AutenticacaoBean {

    private Funcionario funcionarioLogado;

    public Funcionario getFuncionarioLogado() {

        if (funcionarioLogado == null) {
            funcionarioLogado = new Funcionario();
        }
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public String autenticar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(), 
                    DigestUtils.md5Hex(funcionarioLogado.getSenha())); 

            if (funcionarioLogado == null) {
                Facesutil.adicionarMsgErro("CPF e/ou senha inválidos");
                return null;
            } else {
                Facesutil.adicionarMsgInfo("Funcionario autenticado com sucesso");
                return "/principal.xhtml";
            }

        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro tentando autenticar no sistema" + ex.getMessage());
            return null;

        }
    }

    public String sair() { //Métodos que retornam String retornam um destino
        funcionarioLogado = null;
        return "/autenticacao.xhtml?faces-redirect=true";  //outcome via JAVA
    }

}

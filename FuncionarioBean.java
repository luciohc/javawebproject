    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.bean;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.Facesutil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Lucio Henrique
 */
@ManagedBean
@ViewScoped
public class FuncionarioBean {

    private Funcionario funcionarioCadastro;
    private List<Funcionario> listaFuncionario;
    private List<Funcionario> listaFuncionarioFiltrados;
    
    private String acao;
    private Long codigo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncionarioCadastro() {
        
        return funcionarioCadastro;
    }

    public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
        this.funcionarioCadastro = funcionarioCadastro;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
       

    public void novo() {
        funcionarioCadastro = new Funcionario();
    }

    public void salvar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            
            funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha())); //Criptografa a senha
            
            funcionarioDAO.salvar(funcionarioCadastro);
            funcionarioCadastro = new Funcionario();
            Facesutil.adicionarMsgInfo("Funcionario salvo com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar incluir um funcionário: " + ex.getMessage());
        }

    }

    public void carregar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            listaFuncionario = funcionarioDAO.listar();

        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar listar os funcioários:" + ex.getMessage());

        }
    }

    public void carregarCadastro() {
        try {           
            
            
            if (codigo != null) {
                
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.buscarPorCodigo(codigo);
            }else{
                funcionarioCadastro = new Funcionario();
            }
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar obter os dados do funcionário:" + ex.getMessage());
        }

    }

    public void excluir() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.excluir(funcionarioCadastro);
            Facesutil.adicionarMsgInfo("Funcionário removido com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar remover o funcionário: " + ex.getMessage());
        }

    }

    public void editar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            
            funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha())); //Criptografa a senha
            
            funcionarioDAO.editar(funcionarioCadastro);
            
            Facesutil.adicionarMsgInfo("Funcionário editado com sucesso");
        } catch (RuntimeException ex) {
            Facesutil.adicionarMsgErro("Erro ao tentar editar os dados do funcionário: " + ex.getMessage());
        }

    }

}

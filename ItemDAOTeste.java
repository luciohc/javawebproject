/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.teste;

import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.domain.Item;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Lucio Henrique
 */
public class ItemDAOTeste {

    @Test
    @Ignore
    public void salvar() {
        Fabricante f1 = new Fabricante();
        f1.setDescricao("DESCRICAOA");

        Fabricante f2 = new Fabricante();
        f2.setDescricao("DESCRICAOB");

        Fabricante f3 = new Fabricante();

        FabricanteDAO dao = new FabricanteDAO();

        dao.salvar(f1);
        dao.salvar(f2);
        dao.salvar(f3);
    }

    @Test
    @Ignore
    public void listar() {

        FabricanteDAO dao = new FabricanteDAO();

        List<Fabricante> fabricantes = dao.listar();

        for (Fabricante fabricante : fabricantes) {
            System.out.println(fabricante);
        }

    }

    @Test
    @Ignore
    public void buscarPorCodigo() {
        FabricanteDAO dao = new FabricanteDAO();

        Fabricante f1 = dao.buscarPorCodigo(3L);
        Fabricante f2 = dao.buscarPorCodigo(7L);

        System.out.println(f1);
        System.out.println(f2);

    }

    @Test
    @Ignore
    public void excluir() {
        FabricanteDAO dao = new FabricanteDAO();

        Fabricante fabricante = dao.buscarPorCodigo(2L);

        if (fabricante != null) {
            dao.excluir(fabricante);
        }

// Fabricante fabricante = new Fabricante();
// fabricante.setCodigo(5L);
// fabricante.setDescricao("DESCRICAOAA");
// 
// FabricanteDAO dao = new FabricanteDAO();
// dao.excluir(fabricante); 
    }

    @Test
    @Ignore
    public void excluirporCodigo() {
        FabricanteDAO dao = new FabricanteDAO();
        dao.excluir(3L);

    }

    @Test
    public void editar() {
        Fabricante fabricante = new Fabricante();

        fabricante.setCodigo(1L);
        fabricante.setDescricao("DESCRICAOY");

        FabricanteDAO dao = new FabricanteDAO();
        dao.editar(fabricante);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drogaria.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lucio Henrique
 */
@Entity
@Table(name="tbl_itens")

@NamedQueries({
    
    @NamedQuery(name ="Item.listar", query  = "SELECT item FROM Item item"),
    @NamedQuery(name = "Item.buscarPorCodigo", query = "SELECT item FROM Item item WHERE codigo = :codigo")

})
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ite_codigo")    
    private Long codigo;
    
    @Column(name="ite_valor_parcial", precision = 7, scale = 2, nullable = false)
    private BigDecimal valor;
    
    @Column(name="ite_quantidade", nullable = false)
    private Integer quantidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tbl_vendas_ven_codigo", referencedColumnName = "ven_codigo", nullable = false)
    private Venda venda;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tbl_produtos_pro_codigo", referencedColumnName = "pro_codigo", nullable = false)
    private Produto produto;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Item{" + "codigo=" + codigo + ", valor=" + valor + ", quantidade=" + quantidade + ", venda=" + venda + ", produto=" + produto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
  

}

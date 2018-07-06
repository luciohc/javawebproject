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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Lucio Henrique
 */
@Entity
@Table(name="tbl_produtos")
@NamedQueries({
    
    @NamedQuery(name ="Produto.listar", query  = "SELECT produto FROM Produto produto"),
    @NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE codigo = :codigo")

})
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pro_codigo")
    private Long codigo;
    
    @NotEmpty(message = "O campo descrição é obrigatório")
    @Size(min = 5, max = 50, message="Tamanho inválido para o campo descrição(5-50)")
    @Column(name="pro_descricao", length = 50, nullable = false)
    private String descricao;
    
    @NotNull(message="O campo preço é obrigatório")
    @DecimalMin(value="0.00", message="Informe um valor maior ou igual a zero para o campo preço")
    @DecimalMax(value="99999.99", message="Informe um valor menor que 100 mil para o campo preço")
    @Digits(integer=5, fraction = 2, message="Informe um valor menor que 100 mil para o campo preço")
    @Column(name="pro_preco", precision = 7, scale = 2, nullable = false)
    private BigDecimal preco;
    
    @NotNull(message = "O campo quantidade é obrigatório")
    @Min(value=0, message = "Informe um valor maior ou igual a zero para o campo quantidade")
    @Max(value=9999, message = "Informe um valor menor que 10 mil para o campo quantidade")
    @Column(name="pro_quantidade", nullable = false)
    private Integer quantidade;
    
    @NotNull(message="O campo fabricante é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tbl_fabricantes_fab_codigo", referencedColumnName = "fab_codigo", nullable = false)
    private Fabricante fabricante;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade + ", fabricante=" + fabricante + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Vinicius Lemos
 */

@Entity
public class ItemPedido implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
private ItemPedidoPK id = new ItemPedidoPK();

private Double desconto;
private Integer quantidade;
private double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }
    
    public void setPedido(Pedido pedido){
       id.setPedido(pedido);
    }
    
    
    public Produto getProduto(){
        return id.getProduto();
    }
    
    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    /**
     * @return the id
     */
    public ItemPedidoPK getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    
    public double getSubTotal(){
        return (preco - desconto)* quantidade;
    }
    
    /**
     * @return the desconto
     */
    public Double getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR") );
        StringBuilder builder = new StringBuilder();
        builder.append(getProduto().getNome());
        builder.append(", Qte: ");
        builder.append(getQuantidade());
        builder.append(", Preço unitário: ");
        builder.append(nf.format(getPreco()));
        builder.append(", Subtotal : ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }

 

   



}

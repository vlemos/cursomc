/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.dto;

import com.vlemos.cursomc.domain.Produto;
import java.io.Serializable;

/**
 *
 * @author Vinicius Lemos
 */
public class ProdutoDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private double preco;

    public ProdutoDTO(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.preco = obj.getPreco();
    
    
    }

    
    
    public ProdutoDTO() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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

    
    
    
    
}

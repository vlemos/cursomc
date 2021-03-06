/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.dto;

import com.vlemos.cursomc.domain.Categoria;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vinicius.lemos
 */
public class CategoriaDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5, max = 80, message = "O Tamanho deve ser entre 5 e 80 Caracteres")
    private String nome;

    public CategoriaDTO() {
    }
    
     public CategoriaDTO(Categoria obj) {
         id = obj.getId();
         nome = obj.getNome();
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
    
    
    
}

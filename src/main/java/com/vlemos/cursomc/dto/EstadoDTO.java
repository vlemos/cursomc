/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.dto;

import com.vlemos.cursomc.domain.Estado;
import java.io.Serializable;

/**
 *
 * @author vinicius.lemos
 */
public class EstadoDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;

    public EstadoDTO() {
    }
    
    

    public EstadoDTO(Estado obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}

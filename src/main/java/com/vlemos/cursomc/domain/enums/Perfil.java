/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.domain.enums;

/**
 *
 * @author Vinicius Lemos
 */
public enum Perfil {
    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENTE");
    
        
     private int cod;
    private String descricao;

    /**
     * @return the codigo
     */
    public int getCod() {
        return cod;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }



private Perfil(int cod, String descricao){
    this.cod= cod;
    this.descricao = descricao;
}
    
public static Perfil toEnum(Integer cod){
    if(cod == null){
        return null;
    }
    for(Perfil x: Perfil.values()){
        if(cod.equals(x.getCod())){
            return x;
        }
    }
    throw new IllegalArgumentException("Id Inválido : " + cod );
}
    
}

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
public enum TipoCliente {
    

    PESSOAFISICA(1,"Pessoa Física"),
    PESSOAJURIDICA(2,"Pessoa Jurídica");

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



private TipoCliente(int cod, String descricao){
    this.cod= cod;
    this.descricao = descricao;
}
    
public static TipoCliente toEnum(Integer cod){
    if(cod == null){
        return null;
    }
    for(TipoCliente x: TipoCliente.values()){
        if(cod.equals(x.getCod())){
            return x;
        }
    }
    throw new IllegalArgumentException("Id Inválido : " + cod );
}
    
}

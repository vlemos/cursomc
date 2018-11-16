/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vlemos.cursomc.domain.enums.EstadoPagamento;
import javax.persistence.Entity;

/**
 *
 * @author Vinicius Lemos
 */

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{
    private static final long serialVersionUID = 1L;
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao( Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }


    /**
     * @return the numeroDeParcelas
     */
    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    /**
     * @param numeroDeParcelas the numeroDeParcelas to set
     */
    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
    
    
    
}

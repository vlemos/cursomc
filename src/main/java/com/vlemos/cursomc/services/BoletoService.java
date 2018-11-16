/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vinicius Lemos
 */

@Service
public class BoletoService {
    
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date intanteDoPedido){
        //método para adicionar 7 dias como vecimento do boleto
        Calendar cal = Calendar.getInstance();
        cal.setTime(intanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.repositories;

import com.vlemos.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vinicius Lemos
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    // abre uma transação e diz que é apenas consulta, o que impede Lock no Banco
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
    
    
    
}

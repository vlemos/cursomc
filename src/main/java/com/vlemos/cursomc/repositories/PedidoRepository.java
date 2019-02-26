/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.repositories;

import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vinicius Lemos
 */

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    // usando padrão de nomes do SpringBoot
    @Transactional  (readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageable);
}

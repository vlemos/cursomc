/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.repositories;

import com.vlemos.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vinicius Lemos
 */

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.repositories;

import com.vlemos.cursomc.domain.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vinicius Lemos
 */

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
@Transactional(readOnly = true)  // informa ao banco que é somente leitura, com isso evita Lock
public List<Estado> findAllByOrderByNome(); // usando o padrão de nomes do springboot.. find all By Order By + nome do campo, neste exemplo Nome

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.repositories;

import com.vlemos.cursomc.domain.Categoria;
import com.vlemos.cursomc.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vinicius Lemos
 */

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

  //  @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% and cat IN :categorias")  
    //public Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
  
    
    // usando padrões de nomes o proprio framework monta a consulta pra vc... este metodo faz a mesma coisa que o de cima.
    @Transactional(readOnly = true)
    public Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
    
}

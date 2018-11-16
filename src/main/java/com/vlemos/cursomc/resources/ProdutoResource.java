/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.resources;

import com.vlemos.cursomc.domain.Categoria;
import com.vlemos.cursomc.domain.Produto;
import com.vlemos.cursomc.dto.CategoriaDTO;
import com.vlemos.cursomc.dto.ProdutoDTO;
import com.vlemos.cursomc.resources.utils.URL;
import com.vlemos.cursomc.services.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vinicius Lemos
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    
    @Autowired
    private ProdutoService service;
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        
        Produto obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }
    
     @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome, 
            @RequestParam(value = "categorias", defaultValue = "") String categorias, 
          
            @RequestParam(value = "page", defaultValue = "0") Integer page, 
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        
        String nomeDecode = URL.decoreParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecode , ids , page, linesPerPage, orderBy, direction);
        
        // converte a lista Categoria para CategoriaDto
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        
        return ResponseEntity.ok().body(listDto);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.resources;

import com.vlemos.cursomc.domain.Categoria;
import com.vlemos.cursomc.services.CategoriaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vinicius Lemos
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService service;
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        
        Categoria obj = service.buscar(id);

        return ResponseEntity.ok().body(obj);
    }
    
}

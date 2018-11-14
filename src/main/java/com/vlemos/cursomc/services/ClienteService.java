/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.enums.TipoCliente;
import com.vlemos.cursomc.dto.ClienteDTO;
import com.vlemos.cursomc.repositories.ClienteRepository;
import com.vlemos.cursomc.services.expections.DataIntegrityException;
import com.vlemos.cursomc.services.expections.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vinicius Lemos
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    
    public Cliente find(Integer id) {
        Optional<Cliente> obj;
        obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                  "Objeto Não encontrado! Id: " + id + " Tipo " + Cliente.class.getName()));
        
    }
    
      public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um Cliente que possui Endereços");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }
    
    /*
    Retorna todos com paginação
    */
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage,Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }
    
    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        
    }

}

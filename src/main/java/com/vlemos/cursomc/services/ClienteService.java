/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.Cidade;
import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.Endereco;
import com.vlemos.cursomc.domain.enums.Perfil;
import com.vlemos.cursomc.domain.enums.TipoCliente;
import com.vlemos.cursomc.dto.ClienteDTO;
import com.vlemos.cursomc.dto.ClienteNewDTO;
import com.vlemos.cursomc.repositories.ClienteRepository;
import com.vlemos.cursomc.repositories.EnderecoRepository;
import com.vlemos.cursomc.security.UserSS;
import com.vlemos.cursomc.services.expections.AuthorizationException;
import com.vlemos.cursomc.services.expections.DataIntegrityException;
import com.vlemos.cursomc.services.expections.ObjectNotFoundException;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vinicius Lemos
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    
      @Autowired
    private ImageService imageService;
      
    @Autowired
    private S3Service s3Service;
    
     @Autowired
    private EnderecoRepository enderecoRepository;
     
      @Autowired
    private BCryptPasswordEncoder pe;
      
      @Value("${img.prefix.client.profile}")
      private String prefix;
      
      @Value("${img.profile.size}")
      private Integer size;
    
    
    public Cliente find(Integer id) {
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso Negado");
        }
        
        Optional<Cliente> obj;
        obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                  "Objeto Não encontrado! Id: " + id + " Tipo " + Cliente.class.getName()));
        
    }
    
    @Transactional  
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
            throw new DataIntegrityException("Não é possivel excluir um Cliente com pedidos relacionados");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }
    
    public Cliente findByEmail(String email){
        UserSS user = UserService.authenticated();
        
        if(user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())){
            throw new  AuthorizationException("Acesso Negado");
        }
        
        Cliente obj = repo.findByEmail(email);
        if(obj == null){
            throw new ObjectNotFoundException("Objeto Não encontrado! id: " + user.getId() + "Tipo: " + Cliente.class.getName());
        }
        
        return obj;
    }
    
    /*
    Retorna todos com paginação
    */
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage,Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }
    
    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        
    }

 
//DTO para uma inclusão
    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()),pe.encode(objDto.getSenha()));
       
        //Optional<Cidade> cid = cidadeRepository.findById(objDto.getCidadeId());
        Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2()!= null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3()!= null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        
        return cli;
    }
    
    public URI uploadProfilePicture(MultipartFile multiPartFile){
          UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Acesso Negado");
        }
        
        BufferedImage jpgImage = imageService.getJpgFromFile(multiPartFile);
        // recorta e redimensiona a imagem
        jpgImage  = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = prefix + user.getId() + ".jpg"; // definindo o nome do arquivo baseado no codigo do cliente
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
        
      
    }

}

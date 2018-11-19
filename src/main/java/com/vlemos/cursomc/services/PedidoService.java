/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.ItemPedido;
import com.vlemos.cursomc.domain.PagamentoComBoleto;
import com.vlemos.cursomc.domain.Pedido;
import com.vlemos.cursomc.domain.Produto;
import com.vlemos.cursomc.domain.enums.EstadoPagamento;
import com.vlemos.cursomc.repositories.ItemPedidoRepository;
import com.vlemos.cursomc.repositories.PagamentoRepository;
import com.vlemos.cursomc.repositories.PedidoRepository;
import com.vlemos.cursomc.services.expections.ObjectNotFoundException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vinicius Lemos
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    
    @Autowired
    private BoletoService boletoService; 
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
     @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ClienteService clienteService;
    
    public Pedido find(Integer id) {
        Optional<Pedido> obj;
        obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                  "Objeto Não encontrado! Id: " + id + " Tipo " + Pedido.class.getName()));
        
    }

    @Transactional
    public Pedido insert(Pedido obj) {
////        obj.setId(null);
////        obj.setInstante(new Date());
////        obj.getPagamento().setEstado(EstadoPagamento.PENDNETE);
////        obj.getPagamento().setPedido(obj);
////        if(obj.getPagamento() instanceof PagamentoComBoleto){
////            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
////            boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());   
////        }
////        obj = repo.save(obj);
////        pagamentoRepository.save(obj.getPagamento());
////        for(ItemPedido ip : obj.getItens()){
////            ip.setDesconto(0.0);
////            System.out.println("Produto " + ip.getProduto().getId());
////            Produto p = produtoService.find(ip.getProduto().getId());
////            ip.setPreco(p.getPreco());
////            ip.setPedido(obj);
////        }
////        itemPedidoRepository.saveAll(obj.getItens());
////        return obj;
////        
////        obj.setId(null);

		obj.setInstante(new Date());

		obj.getPagamento().setEstado(EstadoPagamento.PENDNETE);
                
                obj.setCliente(clienteService.find(obj.getCliente().getId()));
                
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {

			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();

			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());

		}

		obj = repo.save(obj);

		pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {

			ip.setDesconto(0.0);
                        ip.setProduto(produtoService.find(ip.getProduto().getId()));

			ip.setPreco(ip.getProduto().getPreco());

			ip.setPedido(obj);

		}

		itemPedidoRepository.saveAll(obj.getItens());
                System.out.println(obj);
		return obj;
    }

}

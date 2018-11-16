package com.vlemos.cursomc;

import com.vlemos.cursomc.domain.Categoria;
import com.vlemos.cursomc.domain.Cidade;
import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.Endereco;
import com.vlemos.cursomc.domain.Estado;
import com.vlemos.cursomc.domain.ItemPedido;
import com.vlemos.cursomc.domain.Pagamento;
import com.vlemos.cursomc.domain.PagamentoComBoleto;
import com.vlemos.cursomc.domain.PagamentoComCartao;
import com.vlemos.cursomc.domain.Pedido;
import com.vlemos.cursomc.domain.Produto;
import com.vlemos.cursomc.domain.enums.EstadoPagamento;
import com.vlemos.cursomc.domain.enums.TipoCliente;
import com.vlemos.cursomc.repositories.CategoriaRepository;
import com.vlemos.cursomc.repositories.CidadeRepository;
import com.vlemos.cursomc.repositories.ClienteRepository;
import com.vlemos.cursomc.repositories.EnderecoRepository;
import com.vlemos.cursomc.repositories.EstadoRepository;
import com.vlemos.cursomc.repositories.ItemPedidoRepository;
import com.vlemos.cursomc.repositories.PagamentoRepository;
import com.vlemos.cursomc.repositories.PedidoRepository;
import com.vlemos.cursomc.repositories.ProdutoRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
      
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
    }
}

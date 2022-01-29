package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.service.CarrinhoService;
import com.mercadolibre.w4g9projetofinal.dtos.request.SellOrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/*** Controller dos métodos do carrinho:<br>
 * <b>Adiciona Produtos no Carrinho</b><br>
 * <b>Retira Produtos do Carrinho</b><br>
 * <b>Limpa Carrinho Aberto</b><br>
 * <b>Exibir Carrinho Aberto</b><br>
 * <b>Fechar Carrinho / Gerar Pedido</b>
 *
 * @author Fernando Netto
 */
@RestController
@RequestMapping("/loja")
public class CartController {

	/*** Instancia de serviço: <b>CarrinhoService</b> com notação <i>{@literal @}Autowired</i> do lombok
	 */
	@Autowired
	private CarrinhoService carrinhoService;

	/*** Método para adicionar novo produto ao carrinho de compras do cliente.<br>
	 * POST - /loja/adicionaNoCarrinho/{idCliente}{@literal ?}idProduto={idProduto}{@literal &}qtdProduto={qtdProduto}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param idProduto ID do Produto que o cliente deseja acrescentar no carrinho de compras
	 * @param qtdProduto Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/carrinhoAberto/{idCliente}"
	 * @throws CartManagementException exception
	 */
	@PostMapping("/adicionaNoCarrinho/{idCliente}")
	public ResponseEntity<SellOrderRequestDTO> adicionaProdutosNoCarrinho(
			@PathVariable String idCliente,
			@RequestParam(value = "idProduto", defaultValue = "0") String idProduto,
			@RequestParam(value = "qtdProduto", defaultValue = "0") String qtdProduto,
			UriComponentsBuilder uriBuilder) throws CartManagementException {
			SellOrder sellOrderAberto = null;//carrinhoService.adicionarProdutosNoCarrinho(idCliente,idProduto,qtdProduto);
			URI uri = uriBuilder
					.path("/carrinhoAberto/{idCliente}")
					.buildAndExpand(sellOrderAberto.getIdCliente())
					.toUri();
			return ResponseEntity.created(uri).body(SellOrderRequestDTO.converte(sellOrderAberto));
	}

	/*** Método para retirar uma quantidade "<i>qtdRetirar</i>" de um produto no carrinho de compras do cliente.<br>
	 * GET — /loja/retiraDoCarrinho/{idCliente}{@literal ?}idProduto={idProduto}{@literal &}qtdRetirar={qtdRetirar}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param idProduto ID do Produto que o cliente deseja acrescentar no carrinho de compras
	 * @param qtdRetirar Quantos itens do produto selecionado, o Cliente deseja retirar do carrinho
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/carrinhoAberto/{idCliente}"
	 * @throws CartManagementException excecao
	 */
	@PostMapping("/retiraDoCarrinho/{idCliente}")
	public ResponseEntity<SellOrderRequestDTO>  retiraProdutosDoCarrinho(
			@PathVariable String idCliente,
			@RequestParam(value = "idProduto", defaultValue = "0") String idProduto,
			@RequestParam(value = "qtdRetirar", defaultValue = "0") String qtdRetirar,
			UriComponentsBuilder uriBuilder) throws CartManagementException {
			SellOrder sellOrderAberto = null;//carrinhoService.retirarProdutosDoCarrinho(idCliente,idProduto,qtdRetirar);
			URI uri = uriBuilder
					.path("/carrinhoAberto/{idCliente}")
					.buildAndExpand(sellOrderAberto.getIdCliente())
					.toUri();
			return ResponseEntity.created(uri).body(SellOrderRequestDTO.converte(sellOrderAberto));
	}

	/*** Método para limpar o carrinho de compras do cliente.<br>
	 * POST - /loja/limpaCarrinho/{idCliente}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna mensagem informando que o carrinho está vazio em um ResponseEntity com status <b>CREATED</b> e
	 * 	 * <i>GET</i>: "/loja/carrinhoAberto/{idCliente}"
	 */
	@PostMapping("/limpaCarrinho/{idCliente}")
	public ResponseEntity<String> limpaCarrinho(@PathVariable String idCliente,
								UriComponentsBuilder uriBuilder){
		//carrinhoService.limparCarrinho(idCliente);
		URI uri = uriBuilder
				.path("/carrinhoAberto/{idCliente}")
				.buildAndExpand(idCliente)
				.toUri();
		return ResponseEntity.created(uri).body("Seu carrinho está vazio");
	}

	/*** Método para exibir os produtos do carrinho de compras do cliente.<br>
	 * GET — /loja/carrinhoAberto/{idCliente}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>OK</b>
	 */
	@GetMapping("/carrinhoAberto/{idCliente}")
	public ResponseEntity<SellOrderRequestDTO>  exibirCarrinhoAberto(@PathVariable String idCliente){
		SellOrder sellOrderAberto = null;//carrinhoService.exibirCarrinhoAberto(idCliente);
		return ResponseEntity.ok(SellOrderRequestDTO.converte(sellOrderAberto));
	}

	/*** Método para o cliente fechar o pedido com os produtos no carrinho.<br>
	 * POST — /loja/fechaCarrinho/{idCliente}
	 *
	 * @param idCliente ID do Cliente que está fazendo o pedido
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/pedidos/{id}" implementado no Controller:
	 */
	@PostMapping("/fechaCarrinho/{idCliente}")
	public ResponseEntity<SellOrderRequestDTO> fecharPedido(@PathVariable String idCliente,
															UriComponentsBuilder uriBuilder) {
			SellOrder sellOrder = null;//carrinhoService.fecharCarrinho(idCliente);
			URI uri = uriBuilder
					.path("/pedidos/{id}")
					.buildAndExpand(sellOrder.getId())
					.toUri();
			return ResponseEntity.created(uri).body(SellOrderRequestDTO.converte(sellOrder));
	}
}

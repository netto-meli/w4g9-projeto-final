package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.SellOrderConverter;
import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.service.CartService;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellOrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping(value = "/api/v1/fresh-products/cart")
@PreAuthorize("hasRole('ADMIN') OR hasRole('BUYER')")
public class CartController {

	/*** Instancia de serviço: <b>CarrinhoService</b> com notação <i>{@literal @}Autowired</i> do lombok
	 */
	@Autowired
	private CartService cartService;

	/*** Método para adicionar novo produto ao carrinho de compras do cliente.<br>
	 * POST - /loja/adicionaNoCarrinho/{idCliente}{@literal ?}idProduto={idProduto}{@literal &}qtdProduto={qtdProduto}
	 *
	 * @param idBuyer ID do Cliente que está fazendo o pedido
	 * @param idAdvertise ID do Produto que o cliente deseja acrescentar no carrinho de compras
	 * @param qtdProduct Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/cart/{idCliente}"
	 * @throws CartManagementException exception
	 */
	@PostMapping("/addProduct/{idBuyer}")
	public ResponseEntity<SellOrderResponseDTO> addAdvertiseItemsToCart(
			@PathVariable Long idBuyer,
			@RequestParam(value = "idAdvertise", defaultValue = "0") Long idAdvertise,
			@RequestParam(value = "qtdProduct", defaultValue = "0") int qtdProduct,
			UriComponentsBuilder uriBuilder) throws CartManagementException {
			SellOrder cart = cartService.addAdvertiseItemsToCart(idBuyer, idAdvertise,qtdProduct);
			URI uri = uriBuilder
					.path("/{idBuyer}")
					.buildAndExpand(cart.getBuyer().getId())
					.toUri();
			return ResponseEntity.created(uri).body(SellOrderConverter.convertEntityToDto(cart));
	}

	/*** Método para retirar uma quantidade "<i>qtdRetirar</i>" de um produto no carrinho de compras do cliente.<br>
	 * GET — /loja/retiraDoCarrinho/{idBuyer}{@literal ?}idProduto={idProduto}{@literal &}qtdRetirar={qtdRetirar}
	 *
	 * @param idBuyer ID do Cliente que está fazendo o pedido
	 * @param idAdvertise ID do Produto que o cliente deseja acrescentar no carrinho de compras
	 * @param qtdRemove Quantos itens do produto selecionado, o Cliente deseja retirar do carrinho
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/cart/{idBuyer}"
	 * @throws CartManagementException excecao
	 */
	@DeleteMapping("/removeProduct/{idBuyer}")
	public ResponseEntity<SellOrderResponseDTO>  removeAdvertiseItemsFromCart(
			@PathVariable Long idBuyer,
			@RequestParam(value = "idAdvertise", defaultValue = "0") Long idAdvertise,
			@RequestParam(value = "qtdRemove", defaultValue = "0") int qtdRemove,
			UriComponentsBuilder uriBuilder) throws CartManagementException {
			SellOrder cart = cartService.removeAdvertiseItemsFromCart(idBuyer, idAdvertise,qtdRemove);
			URI uri = uriBuilder
					.path("/{idBuyer}")
					.buildAndExpand(cart.getBuyer().getId())
					.toUri();
			return ResponseEntity.created(uri).body(SellOrderConverter.convertEntityToDto(cart));
	}

	/*** Método para limpar o carrinho de compras do cliente.<br>
	 * POST - /loja/limpaCarrinho/{idBuyer}
	 *
	 * @param idBuyer ID do Cliente que está fazendo o pedido
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna mensagem informando que o carrinho está vazio em um ResponseEntity com status <b>CREATED</b> e
	 * 	 * <i>GET</i>: "/loja/cart/{idBuyer}"
	 */
	@DeleteMapping("/emptyCart/{idBuyer}")
	public ResponseEntity<String> emptyCart(@PathVariable Long idBuyer,
								UriComponentsBuilder uriBuilder){
		cartService.emptyCart(idBuyer);
		URI uri = uriBuilder
				.path("/{idBuyer}")
				.buildAndExpand(idBuyer)
				.toUri();
		return ResponseEntity.created(uri).body("Seu carrinho está vazio");
	}

	/*** Método para exibir os produtos do carrinho de compras do cliente.<br>
	 * GET — /loja/cart/{idCliente}
	 *
	 * @param idBuyer ID do Cliente que está fazendo o pedido
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>OK</b>
	 */
	@GetMapping("/{idBuyer}")
	public ResponseEntity<SellOrderResponseDTO> showCart(@PathVariable Long idBuyer){
		SellOrder cart = cartService.getCart(idBuyer);
		return ResponseEntity.ok(SellOrderConverter.convertEntityToDto(cart));
	}

	/*** Método para o cliente fechar o pedido com os produtos no carrinho.<br>
	 * POST — /loja/fechaCarrinho/{idBuyer}
	 *
	 * @param idBuyer ID do Cliente que está fazendo o pedido
	 * @param uriBuilder UriComponentsBuilder que gera URI para o ResponseEntity
	 * @return Retorna payload de PedidoDTO em um ResponseEntity com status <b>CREATED</b> e
	 * <i>GET</i>: "/loja/pedidos/{id}" implementado no Controller:
	 */
	@PutMapping("/createSellOrder/{idBuyer}")
	public ResponseEntity<SellOrderResponseDTO> createSellOrder(@PathVariable Long idBuyer,
															 UriComponentsBuilder uriBuilder) {
			SellOrder sellOrder = cartService.createSellOrder(idBuyer);
			URI uri = uriBuilder
					.path("/{idBuyer}")
					.buildAndExpand(sellOrder.getId())
					.toUri();
			return ResponseEntity.created(uri).body(SellOrderConverter.convertEntityToDto(sellOrder));
	}
}
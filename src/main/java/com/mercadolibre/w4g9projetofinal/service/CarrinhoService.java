package com.mercadolibre.w4g9projetofinal.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*** Service dos métodos do carrinho:<br>
 * <b>Adiciona Produtos no Carrinho</b><br>
 * <b>Retira Produtos do Carrinho</b><br>
 * <b>Limpa Carrinho Aberto</b><br>
 * <b>Exibir Carrinho Aberto</b><br>
 * <b>Fechar Carrinho / Gerar Pedido</b>
 *
 * @author Fernando Netto
 */
@Service
@AllArgsConstructor
public class CarrinhoService {

    /*** Instancia de repositório: <b>ClienteRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     *
    private ClienteRepository clienteRepository;
    /*** Instancia de repositório: <b>EstoqueRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     *
    private EstoqueRepository estoqueRepository;
    /*** Instancia de repositório: <b>PedidoRepository</b> com notação <i>{@literal @}Autowired</i> do lombok
     *
    private PedidoRepository pedidoRepository;

    /*** Método para adicionar novo produto, ou mais itens de um mesmo produto, ao carrinho de compras do cliente.<br>
     * Criando persistência do carrinho no repositório.
     *
     * @param clienteId ID do Cliente que está fazendo o pedido
     * @param produtoId ID do Produto que o cliente deseja acrescentar no carrinho de compras
     * @param quantidade Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
     * @return Retorna um carrinho de compras (internamente é um <b>Pedido</b> com <i>ID nula</i>,
     * pois se trata um pedido ainda não finalizado).
     * @throws CartManagementException Lança exceção CartManagementException no caso de adicionar zero produtos num carrinho.
     *
    public Pedido adicionarProdutosNoCarrinho(String clienteId, String produtoId, String quantidade) throws CartManagementException {
        Long idCliente = Long.parseLong(clienteId);
        Long idProduto = Long.parseLong(produtoId);
        long qtdProdutos = Long.parseLong(quantidade);
        Pedido carrinho = pedidoRepository.getCarrinho(idCliente);
        ItemCarrinho itemCarrinho;
        if ( Objects.equals( carrinho, null)
                || carrinho.getItemCarrinho(idProduto) == null) {
            if (qtdProdutos <= 0)
                throw new CartManagementException("Impossível iniciar um carrinho com ZERO produtos");
            itemCarrinho = new ItemCarrinho( qtdProdutos, estoqueRepository.get(idProduto) );
        } else {
            itemCarrinho = carrinho.getItemCarrinho(idProduto);
            itemCarrinho.setQuantidade( itemCarrinho.getQuantidade() + qtdProdutos );
        }
        String endereco = clienteRepository.get(idCliente).getEndereco();
        return pedidoRepository.adicionarProdutoNoCarrinho(idCliente, endereco, itemCarrinho);
    }

    /*** Método para retirar uma quantidade de um produto do carrinho de compras do cliente.<br>
     * Criando persistência do carrinho no repositório.
     *
     * @param clienteId ID do Cliente que está fazendo o pedido
     * @param produtoId ID do Produto que o cliente deseja acrescentar no carrinho de compras
     * @param quantidadeRetirar Quantos itens do produto selecionado, o Cliente deseja adicionar no carrinho
     * @return Retorna um <b>Pedido</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     * @throws CartManagementException Erro ao tentar retirar mais itens do carrinho do que os existentes.
     *
    public Pedido retirarProdutosDoCarrinho(String clienteId, String produtoId, String quantidadeRetirar) throws CartManagementException {
        long idCliente = Long.parseLong(clienteId);
        long idProduto = Long.parseLong(produtoId);
        long qtdProdutos = Long.parseLong(quantidadeRetirar);
        Pedido carrinho = pedidoRepository.getCarrinho(idCliente);
        String endereco = clienteRepository.get(idCliente).getEndereco();
        if (!Objects.equals(carrinho, null)
                && carrinho.getItemCarrinho(idProduto) != null) {
            ItemCarrinho item = carrinho.getItemCarrinho(idProduto);
            item.retiraQuantidadeProduto(qtdProdutos);
            carrinho.atualizaCarrinho(item, endereco);
        }
        return carrinho;
    }

    /*** Método para limpar o carrinho de compras de um cliente
     *
     * @param clienteId ID do Cliente que deseja zerar o carrinho de compras
     *
    public void limparCarrinho(String clienteId){
        long idCliente = Long.parseLong(clienteId);
        pedidoRepository.limparCarrinho(idCliente);
    }

    /*** Método para exibir o carrinho de compras atual de um cliente
     *
     * @param clienteId ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>Pedido</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     *
    public Pedido exibirCarrinhoAberto(String clienteId) {
        long idCliente = Long.parseLong(clienteId);
        return pedidoRepository.getCarrinho(idCliente);
    }

    /*** Método para fechar o carrinho de compras de um cliente e criar um pedido
     *
     * @param clienteId ID do Cliente que deseja ver o estado atual do carrinho de compras
     * @return Retorna um <b>Pedido</b>, com <i>ID nula</i>, pois é um pedido ainda não finalizado (carrinho aberto).
     * @throws CartManagementException Lança exceção CartManagementException no caso de fechar carrinho vazio.
     * @throws ErrorProcesamentoException Exceção ao carregar os JSON em memória.
     * @throws RepositoryException Exceção no repositória.
     *
    public Pedido fecharCarrinho(String clienteId) throws CartManagementException, ErrorProcesamentoException, RepositoryException {
        Long idCliente = Long.parseLong(clienteId);
        Pedido carrinho = pedidoRepository.getCarrinho(idCliente);
        List<ItemCarrinho> listItemCarrinho = carrinho.getListaItensCarrinho();
        if (listItemCarrinho.size() == 0)
            throw new CartManagementException("Impossível gerar pedido, utilizando um carrinho vazio.");
        estoqueRepository.baixarEstoque(listItemCarrinho);
        String endereco = clienteRepository.get(idCliente).getEndereco();
        Pedido pedido = pedidoRepository.criaPedido(listItemCarrinho, idCliente, endereco);
        pedidoRepository.limparCarrinho(idCliente);
        return pedido;
    }*/
}

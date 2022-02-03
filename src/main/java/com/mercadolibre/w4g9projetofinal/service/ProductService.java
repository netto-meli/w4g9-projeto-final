package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.enums.OrderByProductInBatch;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe de servico do produto
 *
 * @autor Leonardo
 */
@Service
@AllArgsConstructor
public class ProductService {

    /*** Instancia de ProductRepository*/
    private final ProductRepository repository;

    /*** Instancia de BatchService*/
    private final BatchService batchService;

    /**
     * Metodo que busca todos os produtos
     * @return lista de produtos adastrados
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * Metodo pra buscar o produto por meio do id
     * @param id
     * @return produto do id da busca
     */
    public Product findById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado! Por favor verifique dados informados."));
    }

    /**
     * Metodo que busca produto por categoria
     * @param category
     * Categoria:
     * FF = FROZEN
     * RF = COLD
     * FS = FRESH
     * @return retorna o produto da categoria buscada
     */
    public List<Product> findByCategoryProduct(String category) {
        if (!(category == null)) {
            return repository.findAll().stream().filter(p -> p.getCategoryRefrigeration().getCod().equals(category)).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Metodo para buscar o lote em que o produto esta cadastrado
     * @param idProduct
     * @return lotes em que o produto foi cadastrado
     */
    public List<Batch> findByBatchInProduct(Long idProduct) {
        return batchService.findByProductId(idProduct);
    }

    /**
     * Metodo para ordenar lote em que o produto esta cadastrado
     * @param idProduct
     * @param orderBy
     * Ordenacao:
     * L = Lote
     * C = qtd atual
     * F = data vencimento
     * @return lista de lotes em que o produto esta cadastrado
     */
    public List<Batch> OrderByBatchInProduct(Long idProduct, String orderBy) {
        List<Batch> batch = findByBatchInProduct(idProduct);
        if (OrderByProductInBatch.ORDER_BY_BATCH.getCod().equals(orderBy)) {
            return batch.stream().sorted(Comparator.comparing(Batch::getId)).collect(Collectors.toList());
        } else if(OrderByProductInBatch.ORDER_BY_QUANTITY.getCod().equals(orderBy)){
            return batch.stream().sorted(Comparator.comparing(Batch::getCurrentQuantity)).collect(Collectors.toList());
        } else if(OrderByProductInBatch.ORDER_BY_DUEDATE.getCod().equals(orderBy)){
            return batch.stream().sorted(Comparator.comparing(Batch::getDueDate)).collect(Collectors.toList());
        } else {
            throw new BusinessException("Metodo de Ordenação informado está errado");
        }
    }

}

package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.BatchRepository;
import com.mercadolibre.w4g9projetofinal.repository.ProductRepository;
import com.mercadolibre.w4g9projetofinal.service.BatchService;
import com.mercadolibre.w4g9projetofinal.service.ProductService;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/***
 * @author Leonardo
 */
public class ProductServiceTest {
    @Test
    public void verifyAllProduct() {

        Product product = new Product(2L, "Test", "Product", 0, 9, RefrigerationType.FROZEN);
        Product product2 = new Product(2L, "Test", "Product2", 0, 9, RefrigerationType.COLD);
        Product product3 = new Product(2L, "Test", "Product3", 0, 9, RefrigerationType.FRESH);
        List<Product> listProduct = new ArrayList<>(Arrays.asList(product, product2, product3));

        ProductRepository mockProdRepository = Mockito.mock(ProductRepository.class);
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(mockProdRepository.findAll()).thenReturn(listProduct);

        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(mockProdRepository, batchService);

        List<Product> prodFind = prodService.findAll();

        assertEquals(listProduct, prodFind);
        assertNotNull(prodService);
    }

    @Test
    public void verifyProductById() {
        Product product = new Product(2L, "Test", "Product", 0, 9, RefrigerationType.FROZEN);

        ProductRepository mockProdRepository = Mockito.mock(ProductRepository.class);
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(mockProdRepository.findById(2L)).thenReturn(java.util.Optional.of(product));

        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(mockProdRepository, batchService);

        Product prodFind = prodService.findById(2L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> prodService.findById(1L));

        assertEquals(prodFind, product);
        assertTrue(ex.getMessage().contains("Produto não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void insertProduct() {

        Product product = new Product(2L, "Test", "Product", 0, 9, RefrigerationType.FROZEN);

        ProductRepository mockProdRepository = Mockito.mock(ProductRepository.class);
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(mockProdRepository.save(product)).thenReturn(product);

        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(mockProdRepository, batchService);

        Product prodSave = prodService.insert(product);

        assertEquals(prodSave, product);

    }

    @Test
    public void productUpdate() {
        Product product = new Product(2L, "Test", "Product", 0, 9, RefrigerationType.FROZEN);

        ProductRepository mockProdRepository = Mockito.mock(ProductRepository.class);
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(mockProdRepository.save(product)).thenReturn(product);
        Mockito.when(mockProdRepository.findById(2L)).thenReturn(java.util.Optional.of(product));

        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(mockProdRepository, batchService);

        Product prod = prodService.update(product);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> prodService.findById(1L));

        assertEquals(product, prod);
        assertNotNull(prod);
        assertTrue(ex.getMessage().contains("Produto não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void DeleteBuyer() {
        Product product = new Product(2L, "Test", "Product", 0, 9, RefrigerationType.FROZEN);

        ProductRepository mockProdRepository = Mockito.mock(ProductRepository.class);
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(mockProdRepository.save(product)).thenReturn(product);
        Mockito.doNothing().when(mockProdRepository).delete(product);

        Mockito.when(mockProdRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(mockProdRepository.findById(2L)).thenReturn(Optional.of(product));

        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(mockProdRepository, batchService);

        prodService.delete(2L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> prodService.findById(1L));

        Mockito.verify(mockProdRepository, Mockito.times(1)).delete(product);
        assertTrue(ex.getMessage().contains("Produto não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void findProductInBatch() {
        Long idProduct = 1L;
        List<Batch> batchList = new ArrayList<>();

        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(batchRepository.findByProduct_Id(idProduct)).thenReturn(batchList);

        BatchService bs = new BatchService(batchRepository, sectionService);

        List<Batch> prodInBatch = bs.findByProductId(idProduct);

        Assertions.assertEquals(batchList, prodInBatch);
    }

    @Test
    public void OrderByProductByCategory() {

        List<Batch> list = new ArrayList<>();
        Batch b = new Batch();
        b.setDueDate(LocalDate.now());
        b.setId(2L);
        b.setInitialQuantity(2);

        Batch b2 = new Batch();
        b2.setDueDate(LocalDate.of(2021, 11, 27));
        b2.setId(3L);
        b.setInitialQuantity(24);

        list.add(b);

        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(batchRepository.findByProduct_Id(2L)).thenReturn(list);
        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(productRepository, batchService);

        List<Batch> orderByBatch = prodService.OrderByBatchInProduct(2L, "L");
        List<Batch> orderByBatch2 = prodService.OrderByBatchInProduct(2L, "C");
        List<Batch> orderByBatch3 = prodService.OrderByBatchInProduct(2L, "F");
        BusinessException ex = assertThrows(BusinessException.class, () ->
                prodService.OrderByBatchInProduct(2L, ""));

        Assertions.assertEquals(list, orderByBatch);
        Assertions.assertEquals(list, orderByBatch2);
        Assertions.assertEquals(list, orderByBatch3);
        assertTrue(ex.getMessage().contains("Metodo de Ordenação informado está errado"));
    }

    @Test
    public void findByBatchInProduct() {
        Product product = new Product(2L, "Test", "Product", 0, 9, RefrigerationType.FROZEN);
        Product product2 = new Product(3L, "Test", "Product2", 0, 9, RefrigerationType.COLD);
        Product product3 = new Product(4L, "Test", "Product3", 0, 9, RefrigerationType.FRESH);
        Product product4 = new Product(5L, "Test", "Product2", 0, 9, RefrigerationType.COLD);
        Product product5 = new Product(6L, "Test", "Product2", 0, 9, RefrigerationType.COLD);
        List<Product> listProduct = new ArrayList<>(Arrays.asList(product, product2, product3, product4, product5));

        ProductRepository mockProdRepository = Mockito.mock(ProductRepository.class);
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);

        Mockito.when(mockProdRepository.findByCategoryRefrigeration(RefrigerationType.COLD)).thenReturn(listProduct);

        BatchService batchService = new BatchService(batchRepository, sectionService);
        ProductService prodService = new ProductService(mockProdRepository, batchService);

        List<Product> prodFind = prodService.findByCategoryProduct(RefrigerationType.COLD);

        assertEquals(listProduct, prodFind);
        assertNotNull(prodService);
    }

}

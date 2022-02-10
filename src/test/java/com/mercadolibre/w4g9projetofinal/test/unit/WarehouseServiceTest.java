package com.mercadolibre.w4g9projetofinal.test.unit;


    import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
	import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
	import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
	import com.mercadolibre.w4g9projetofinal.service.WarehouseService;
	import org.junit.jupiter.api.Test;
	import org.mockito.Mockito;



	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
	import java.util.Optional;

	import static org.junit.jupiter.api.Assertions.*;

/***
 * @author Rafael Menezes
 */

public class WarehouseServiceTest {
        @Test
        public void verificaBuscaDeWarehouse() {

            Warehouse wh = new Warehouse(2L,"Testando","São Paulo");
            Warehouse wh1 = new Warehouse(3L,"Testando1","Rio de Janeiro");
            Warehouse wh2 = new Warehouse(4L,"Testando2","Belo Horizonte");
            Warehouse wh3 = new Warehouse(5L,"Testando3","Curitiba");
            List<Warehouse> list = new ArrayList<>(Arrays.asList(wh, wh1, wh2, wh3));

            WarehouseRepository mockWarehouseRepository = Mockito.mock(WarehouseRepository.class);
            Mockito.when(mockWarehouseRepository.findAll()).thenReturn(list);

            WarehouseService warehouseService = new WarehouseService(mockWarehouseRepository,null,null);
            List<Warehouse> warehouseFind = warehouseService.findAll();

            assertEquals(list, warehouseFind);

        }

        @Test
        public void verificaWarehousePorId() {
            Warehouse warehouse = new Warehouse(1L,"Melicidade","São Paulo");

            WarehouseRepository mockWarehouseRepository = Mockito.mock(WarehouseRepository.class);
            Mockito.when(mockWarehouseRepository.findById(1L)).thenReturn(java.util.Optional.of(warehouse));

            WarehouseService warehouseService = new WarehouseService(mockWarehouseRepository,null,null);

            Warehouse warehouseFind = warehouseService.findById(1L);

            assertEquals(warehouseFind, warehouse);
        }


        @Test
        public void verificarInsercaoDoArmazem() {
            Warehouse warehouse = new Warehouse(4L, "Testando2", "São Paulo");
            Warehouse warehouse1 = new Warehouse(5L, "Testando3", "Florianópolis");

            WarehouseRepository mockWarehouseRepository = Mockito.mock(WarehouseRepository.class);
            Mockito.when(mockWarehouseRepository.save(warehouse)).thenReturn(warehouse);


            WarehouseService warehouseService = new WarehouseService(mockWarehouseRepository, null, null);
            Warehouse warehouseFind = warehouseService.insert(warehouse);


            assertEquals(warehouseFind, warehouse);
        }

        @Test
        public void AtualizarArmazem() {
            Warehouse warehouse2 = new Warehouse(3L, "Melicidade", "São Paulo");

            WarehouseRepository mockWarehouseRepository = Mockito.mock(WarehouseRepository.class);
            Mockito.when(mockWarehouseRepository.save(warehouse2)).thenReturn(warehouse2);

            Mockito.when(mockWarehouseRepository.findById(3L)).thenReturn(java.util.Optional.of(warehouse2));
            WarehouseService warehouseService = new WarehouseService(mockWarehouseRepository,null,null);

            Warehouse warehouse = warehouseService.update(warehouse2);

            assertEquals(warehouse2, warehouse);
            assertNotNull(warehouse);
        }
        @Test
        public void deletarArmazem(){
            Warehouse warehouse = new Warehouse(1L,"Melicidade","São Paulo");

            WarehouseRepository mockWarehouseRepository = Mockito.mock(WarehouseRepository.class);
            Mockito.when(mockWarehouseRepository.save(warehouse)).thenReturn(warehouse);
            Mockito.doNothing().when(mockWarehouseRepository).delete(warehouse);
            Mockito.when(mockWarehouseRepository.findById(3L)).thenReturn(Optional.empty());
            Mockito.when(mockWarehouseRepository.findById(3L)).thenReturn(Optional.of(warehouse));

            WarehouseService warehouseService = new WarehouseService(mockWarehouseRepository, null, null);

            warehouseService.delete(3L);

            Mockito.verify(mockWarehouseRepository, Mockito.times(1)).delete(warehouse);
        }


    }



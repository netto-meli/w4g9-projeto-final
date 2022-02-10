package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.AdvertiseRepository;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/***
 * @author Leonardo
 */
public class AdvertiseServiceTest {

    @Test
    public void verifyAllAdvertiseS() {

        Advertise adv = new Advertise(1L,"primeiro",new Product(), new Seller(),new BigDecimal(4),AdvertiseStatus.ATIVO, true);
        Advertise adv1 = new Advertise(2L,"segundo",new Product(), new Seller(),new BigDecimal(8),AdvertiseStatus.INATIVO, false);
        Advertise adv2 = new Advertise(3L,"terceiro",new Product(), new Seller(),new BigDecimal(12),AdvertiseStatus.FINALIZADO, true);
        List<Advertise> listAdvertise = new ArrayList<>(Arrays.asList(adv, adv1, adv2));

        AdvertiseRepository advertiseRepository = Mockito.mock(AdvertiseRepository.class);
        Mockito.when(advertiseRepository.findAll()).thenReturn(listAdvertise);

        AdvertiseService advertiseService = new AdvertiseService(advertiseRepository);
        List<Advertise> AdFindAll = advertiseService.findAll();

        assertEquals(listAdvertise, AdFindAll);
        assertNotNull(advertiseService);
    }

    @Test
    public void verifyAdvertiseById() {
        Advertise adv = new Advertise(1L,"primeiro",new Product(), new Seller(),new BigDecimal(4),AdvertiseStatus.ATIVO, true);

        AdvertiseRepository advertiseRepository = Mockito.mock(AdvertiseRepository.class);
        Mockito.when(advertiseRepository.findById(1L)).thenReturn(java.util.Optional.of(adv));

        AdvertiseService advertiseService = new AdvertiseService(advertiseRepository);

        Advertise adFindId = advertiseService.findById(1L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> advertiseService.findById(2L));

        assertEquals(adFindId, adv);
        assertTrue(ex.getMessage().contains("Anuncio não encontrado! Por favor verifique o id."));
    }

    @Test
    public void insertAdvertise() {

        Advertise adv = new Advertise(1L, "primeiro", new Product(), new Seller(), new BigDecimal(4), AdvertiseStatus.ATIVO, true);

        AdvertiseRepository advertiseRepository = Mockito.mock(AdvertiseRepository.class);
        Mockito.when(advertiseRepository.save(adv)).thenReturn(adv);

        AdvertiseService advertiseService = new AdvertiseService(advertiseRepository);
        Advertise adInsert = advertiseService.insert(adv);

        assertEquals(adv, adInsert);
    }

    @Test
    public void AdvertiseUpdate(){
        Advertise adv1 = new Advertise(2L,"segundo",new Product(), new Seller(),new BigDecimal(8),AdvertiseStatus.INATIVO, false);
        Advertise adv2 = new Advertise(3L,"terceiro",new Product(), new Seller(),new BigDecimal(12),AdvertiseStatus.FINALIZADO, true);
        List<Advertise> listAdvertise = new ArrayList<>(Arrays.asList(adv1, adv2));

        Advertise adv = new Advertise(1L,"primeiro",new Product(), new Seller(1L,"um ai","admim","leota@gmail.com","dfdfddf",listAdvertise),new BigDecimal(4),AdvertiseStatus.ATIVO, true);

        AdvertiseRepository advertiseRepository = Mockito.mock(AdvertiseRepository.class);
        Mockito.when(advertiseRepository.save(adv)).thenReturn(adv);

        Mockito.when(advertiseRepository.findById(1L)).thenReturn(java.util.Optional.of(adv));
        AdvertiseService advertiseService = new AdvertiseService(advertiseRepository);

        Advertise advUpdate = advertiseService.update(adv);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> advertiseService.findById(4L));

        assertEquals(adv, advUpdate);
        assertNotNull(adv);
        assertTrue(ex.getMessage().contains("Anuncio não encontrado! Por favor verifique o id."));
    }

    @Test
    public void DeleteBuyer() {
        Advertise adv1 = new Advertise(2L,"segundo",new Product(), new Seller(),new BigDecimal(8),AdvertiseStatus.INATIVO, false);
        Advertise adv2 = new Advertise(3L,"terceiro",new Product(), new Seller(),new BigDecimal(12),AdvertiseStatus.FINALIZADO, true);
        List<Advertise> listAdvertise = new ArrayList<>(Arrays.asList(adv1, adv2));

        Advertise adv = new Advertise(1L,"primeiro",new Product(), new Seller(1L,"um ai","admim","leota@gmail.com","dfdfddf",listAdvertise),new BigDecimal(4),AdvertiseStatus.ATIVO, true);

        AdvertiseRepository advertiseRepository = Mockito.mock(AdvertiseRepository.class);
        Mockito.when(advertiseRepository.save(adv)).thenReturn(adv);
        Mockito.doNothing().when(advertiseRepository).delete(adv);
        Mockito.when(advertiseRepository.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(advertiseRepository.findById(1L)).thenReturn(Optional.of(adv));

        AdvertiseService advertiseService = new AdvertiseService(advertiseRepository);

        advertiseService.delete(1L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> advertiseService.findById(2L));

        Mockito.verify(advertiseRepository, Mockito.times(1)).delete(adv);
        assertTrue(ex.getMessage().contains("Anuncio não encontrado! Por favor verifique o id."));
    }

}

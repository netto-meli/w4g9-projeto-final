package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Classe de serviço para Seller
 *
 * @author Marcos Sá
 */

@Service
@AllArgsConstructor
public class SellerService {

    /*** Instancia de BCryptPasswordEncoder: <b>BCryptPasswordEncoder</b>.
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*** Instancia de repositório: <b>SellerRepository</b>.
     */
    private SellerRepository repository;

    /*** Instancia de service: <b>AdvertiseService</b>.
     */
    private AdvertiseService advertiseService;

    /*** Método que retorna lista de Sellers
     *
     * @return lista de compradores
     */
    public List<Seller> findAll() {
        return repository.findAll();
    }

    /*** Método que busca Seller por Id
     * @param id ID do Seller a ser retornado
     * @return comprador
     */
    public Seller findById(Long id) {
        Optional<Seller> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Vendedor não encontrado! Por favor verifique o id."));
    }

    /*** Método que insere um Seller
     * @param obj objeto Seller a ser inserido
     * @return comprador
     */
    public Seller insert(Seller obj) {
        obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new ExistingUserException("Username ou Email existente na base de dados");
        }
    }

    /*** Método que atualiza um Seller já existente
     *
     * @param newObj Objeto com informações para atualização de um seller existente
     * @return comprador
     */
    public Seller update(Seller newObj) {
        Seller obj = findById(newObj.getId());
        updateSeller(newObj, obj);
        obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        return repository.save(obj);
    }

    /*** Método deleta um Seller do Bando de dados
     *
     * @param id ID do Seller a ser deletado
     */
    public void delete(Long id) {
        Seller obj = findById(id);
        repository.delete(obj);
    }

    //Método para update de Seller
    private static void updateSeller(Seller obj, Seller newObj) {
        newObj.setUsername(obj.getUsername());
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
    }

    /***
     * Verifica o vendedor na lista de lote
     * @param batchList lista de lote
     * @return vendedor
     */
    public Seller verifySellerInInboundOrder(List<Batch> batchList) {
        Long sellerId = null;
        Advertise ad = new Advertise();
        for (Batch b :batchList) {
            Long batchSellerId = repository.findSellerByAdvertiseId(b.getAdvertise().getId())
                    .orElseThrow( () -> new ObjectNotFoundException("Seller not found."));
            if (sellerId == null) {
                ad = advertiseService.findById(b.getAdvertise().getId());
                sellerId = ad.getSeller().getId();
            }
            if(!sellerId.equals(batchSellerId)) throw new SectionManagementException("Different Sellers in Batch List");
        }
        return ad.getSeller();
    }
}

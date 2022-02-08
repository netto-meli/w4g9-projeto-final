package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.repository.SellOrderRepository;
import com.mercadolibre.w4g9projetofinal.repository.TransporterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryService {
    /*** Instancia de repositório: <b>SellOrderRepository</b>.
     */
    private SellOrderRepository sellOrderRepository;
    /*** Instancia de repositório: <b>TransporterRepository</b>.
     */
    private TransporterRepository transporterRepository;

    public void findAll(){

    }

    public void findById(){

    }

    public void findByStatus(){
        //on route or not

    }

    public void insert(){

    }

    public void update(){

    }

    public void delete(){

    }

    public void callTransporter(){
// lista todos q cabem delivery + nao em rota
    }

    public void confirmDelivery(){

    }

}

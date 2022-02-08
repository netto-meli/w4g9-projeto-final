package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.repository.SellOrderRepository;
import com.mercadolibre.w4g9projetofinal.repository.TransporterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransporterService {
    /*** Instancia de repositório: <b>SellOrderRepository</b>.
     */
    private SellOrderRepository sellOrderRepository;
    /*** Instancia de repositório: <b>TransporterRepository</b>.
     */
    private TransporterRepository transporterRepository;

    public void findAll(){

    }

    public void findById(){
        //on route or not

    }

    public void findByStatus(){

    }

    public void insertTransporter(){

    }

    public void update(){

    }

    public void delete(){

    }

    public void call(){
// lista todos q cabem delivery + nao em rota
    }

    public void confirmDelivery(){

    }

}

package com.mercadolibre.w4g9projetofinal.dtos.request;

import java.util.ArrayList;

public class InboundOrderRequestDTO {
        public String orderNumber;
        public String orderDate;
        public SectionRequestDTO section;
        public ArrayList<BatchStockRequestDTO> batch5tock;
}

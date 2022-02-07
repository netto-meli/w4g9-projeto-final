package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderRequestDTO {
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long orderNumber;
    @NotNull(message = "Campo Obrigatório")
    @PastOrPresent(message = "Data da ordem de entrada não pode ser uma data futura.")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;
    @Valid
    @NotNull(message = "Campo Obrigatório")
    private SectionRequestDTOForInboundOrder section;
    @Valid
    @NotNull(message = "Campo Obrigatório")
    @NotEmpty(message = "Lista de Lotes não pode estar vazia")
    private List<BatchRequestDTO> batchStock;
}

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
       /* @NotNull(message = "A disciplina é obrigatória")
        @Size(min = 8, max = 50, message = "tamanho minimo 8, máximo 50")
        @Pattern(regexp = "^[-'a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Apenas caracteres do alfabeto, incluindo acentos")
        @NotNull(message = "nota da disciplina é obrigatória")
        @Positive(message = "Largura deve ser maior que 0")
        @Max(value = 10, message = "Nota não pode ser maior que 10")
        @Min(value = 0, message = "Nota não pode ser menor que 0")
        @Digits(integer = 2, fraction = 2, message = "Nota não válida. Aceito apenas de 0 a 10, com 2 dígitos decimais")
        //todo fazer mais validacoes*/
        private int orderNumber;
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        private LocalDate orderDate;
        private SectionRequestDTOForInboundOrder section;
        @Valid
        @NotNull(message = "aluno deve ter disciplinas")
        private List<BatchRequestDTO> batchStock;
}

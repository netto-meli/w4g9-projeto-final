package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchRequestDTO {
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long batchNumber;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Id deve ser um valor positivo")
    private Long advertiseId;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 2, fraction = 2, message = "Temperatura atual inválida. Aceito apenas 2 dígitos decimais")
    @Max(value = 100 , message = "Temperatura atual tem que ser menor que 100")
    @Min(value = -100 , message = "Temperatura atual tem que ser maior que -100")
    private float currentTemperature;
    @NotNull(message = "Campo Obrigatório")
    @Digits(integer = 2, fraction = 2, message = "Temperatura mínima inválida. Aceito apenas 2 dígitos decimais")
    @Max(value = 100 , message = "Temperatura mínima tem que ser menor que 100")
    @Min(value = -100 , message = "Temperatura mínima tem que ser maior que -100")
    private float minimumTemperature;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "Quantidade inicial deve ser maior que 0")
    private int initialQuantity;
    @NotNull(message = "Campo Obrigatório")
    @PositiveOrZero(message = "Quantidade atual não pode ser negativa.")
    private int currentQuantity;
    @NotNull(message = "Campo Obrigatório")
    @PastOrPresent(message = "Data de fabricação não pode ser uma data futura.")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate manufacturingDate;
    @NotNull(message = "Campo Obrigatório")
    @PastOrPresent(message = "Data de fabricação não pode ser uma data futura.")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime manufacturingTime;
    @NotNull(message = "Campo Obrigatório")
    @FutureOrPresent(message = "Data de validade não pode ser uma data passada.")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dueDate;
}
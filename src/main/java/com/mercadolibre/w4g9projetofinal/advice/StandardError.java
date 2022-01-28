package com.mercadolibre.w4g9projetofinal.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

<<<<<<< HEAD
=======
/***
 * Classe StandardError que gera retorno padrão para respostas de erros
 *
 * @author Marcos Sá
 */

>>>>>>> cc4bc4c2dc8c4c5a0380c4a1aae0139151cd7efc
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {
<<<<<<< HEAD
=======
    private static final long serialVersionUID = 1945951435369178405L;
>>>>>>> cc4bc4c2dc8c4c5a0380c4a1aae0139151cd7efc

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
<<<<<<< HEAD
=======

>>>>>>> cc4bc4c2dc8c4c5a0380c4a1aae0139151cd7efc
}

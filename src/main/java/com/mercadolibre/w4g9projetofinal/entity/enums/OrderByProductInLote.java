package com.mercadolibre.w4g9projetofinal.entity.enums;

/***
 * Classe Enum para Ordenacao de produto
 * @author Leonardo
 */
public enum OrderByProductInLote {
    ORDER_BY_LOTE("L", "Lote"),
    ORDER_BY_QUANTITY("C", "Qtd atual"),
    ORDER_BY_DUEDATE("F", "Data vencimento");

    private final String cod;
    private final String descricao;

    private OrderByProductInLote(String cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public String getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static OrderByProductInLote toEnum(String cod) {
        if (cod == null) {
            return null;
        }

        for (OrderByProductInLote prod : OrderByProductInLote.values()) {
            if (cod.equals(prod.getCod())) {
                return prod;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }
}

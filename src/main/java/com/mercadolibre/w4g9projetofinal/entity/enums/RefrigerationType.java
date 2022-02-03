package com.mercadolibre.w4g9projetofinal.entity.enums;

/***
 * Classe Enum para tipo de refrigeracao
 * @author Leonardo
 */
public enum RefrigerationType {

    FROZEN("FF", "Congelado"),
    COLD("RF", "Resfriado"),
    FRESH("FS", "Fresco");

    private final String cod;
    private final String descricao;

    RefrigerationType(String cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public String getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static RefrigerationType toEnum(String cod) {
        if (cod == null) {
            throw new IllegalArgumentException("Id Inválido: " + cod);
        }

        for (RefrigerationType x : RefrigerationType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}

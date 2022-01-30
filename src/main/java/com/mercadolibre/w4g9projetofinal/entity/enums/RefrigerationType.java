package com.mercadolibre.w4g9projetofinal.entity.enums;

public enum RefrigerationType {

    FROZEN("FF", "Congelado"),
    COLD("RF", "Resfriado"),
    FRESH("FS", "Fresco");

    private String cod;
    private String descricao;

    private RefrigerationType(String cod, String descricao) {
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
            return null;
        }

        for (RefrigerationType x : RefrigerationType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}

package com.mercadolibre.w4g9projetofinal.entity.enums;

public enum Profile {
    ADMIN(1, "ROLE_ADMIN"),
    REPRESENTATIVE(2, "ROLE_REPRESENTATIVE"),
    SELLER(3, "ROLE_SELLER"),
    BUYER(4, "ROLE_BUYER");

    private final int cod;
    private final String descricao;

    Profile(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException( "Id inválido: " + cod );
    }
}

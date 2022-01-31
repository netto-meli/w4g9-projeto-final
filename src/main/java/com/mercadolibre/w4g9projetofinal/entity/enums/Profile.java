package com.mercadolibre.w4g9projetofinal.entity.enums;

public enum Profile {

    USUARIO(1, "Usuário"),
    ADMIN(2, "Admin");

    private int cod;
    private String descricao;

    private Profile(int cod, String descricao) {
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

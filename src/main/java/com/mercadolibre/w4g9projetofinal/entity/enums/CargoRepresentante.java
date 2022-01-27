package com.mercadolibre.w4g9projetofinal.entity.enums;

public enum CargoRepresentante {

    SUPERVISOR(0, "Supervisor"),
    LIDER(1, "Líder");

    private int cod;
    private String descricao;

    private CargoRepresentante(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static CargoRepresentante toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(CargoRepresentante x : CargoRepresentante.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}

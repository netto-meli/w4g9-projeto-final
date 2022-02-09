package com.mercadolibre.w4g9projetofinal.entity.enums;

public enum RepresentativeJob {

    SUPERVISOR(0, "Supervisor"),
    LIDER(1, "Líder");

    private final int cod;
    private final String descricao;

    RepresentativeJob(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static RepresentativeJob toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (RepresentativeJob x : RepresentativeJob.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}

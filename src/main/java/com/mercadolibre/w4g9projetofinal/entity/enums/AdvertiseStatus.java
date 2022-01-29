package com.mercadolibre.w4g9projetofinal.entity.enums;

public enum AdvertiseStatus {

    ATIVO(0, "Ativo"),
    INATIVO(1, "Inativo"),
    PAUSADO(2, "Pausado"),
    REMOVIDO(3, "Removido"),
    PENDENTE(4, "Pendente"),
    FINALIZADO(5, "Finalizado");

    private int cod;
    private String descricao;

    private AdvertiseStatus(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static AdvertiseStatus toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(AdvertiseStatus x : AdvertiseStatus.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}

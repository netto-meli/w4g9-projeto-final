package com.mercadolibre.w4g9projetofinal.entity.enums;

/***
 * Classe Enum para status do anuncio
 * @author Leonardo
 */
public enum SellOrderStatus {

    CART(0, "Cart"),
    CREATED(1, "Order Created"),
    PAID(2,"Order Paid"),
    SEPARATION(3, "Products in Separation"),
    SHIPPED(4, "Order Shipped"),
    DELIVERED(5, "Order Dilivered"),
    NOT_DELIVERED(6, "Order Not Dilivered"),
    CANCELED(7,"Order Canceled");

    private final int cod;
    private final String descricao;

    SellOrderStatus(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static SellOrderStatus toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (SellOrderStatus x : SellOrderStatus.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}

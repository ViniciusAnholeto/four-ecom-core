package br.com.four.ecom.core.infrastructure.database.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderId implements Serializable {
    private String orderId;
    private String productId;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderId orderId1)) return false;

        if (!orderId.equals(orderId1.orderId)) return false;
        if (!productId.equals(orderId1.productId)) return false;
        return price.equals(orderId1.price);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * orderId.hashCode() + productId.hashCode()) + price.hashCode();
    }
}

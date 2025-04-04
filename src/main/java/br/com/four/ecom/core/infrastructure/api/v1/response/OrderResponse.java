package br.com.four.ecom.core.infrastructure.api.v1.response;

import br.com.four.ecom.core.domains.orders.models.OrderModel;
import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private String status;

    public OrderResponse(OrderModel orderModel) {
        this.id = orderModel.getId();
        this.userId = orderModel.getUserId();
        this.totalPrice = orderModel.getTotalPrice();
        this.status = orderModel.getStatus();
    }
}

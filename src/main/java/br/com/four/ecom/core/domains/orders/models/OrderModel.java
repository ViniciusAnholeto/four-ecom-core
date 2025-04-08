package br.com.four.ecom.core.domains.orders.models;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderModel {
    private Long orderId;
    private Long userId;
    private Double totalPrice;
    private OrderStatusEnum status;
    private List<OrderProductsModel> products;
}

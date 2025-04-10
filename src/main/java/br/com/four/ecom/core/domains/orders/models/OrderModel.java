package br.com.four.ecom.core.domains.orders.models;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderModel {
    private String orderId;
    private String customerId;
    private Double totalPrice;
    private OrderStatusEnum status;
    private List<OrderProductsModel> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

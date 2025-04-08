package br.com.four.ecom.core.infrastructure.api.v1.response;

import br.com.four.ecom.core.domains.orders.models.OrderModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private Double totalPrice;
    private String status;
    private List<OrderProductsModel> products;

    public OrderResponse(OrderModel orderModel) {
        this.orderId = orderModel.getOrderId();
        this.userId = orderModel.getUserId();
        this.totalPrice = orderModel.getTotalPrice();
        this.status = orderModel.getStatus().name();
        this.products = orderModel.getProducts().stream()
                .map(product -> OrderProductsModel.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .quantity(product.getQuantity())
                        .build())
                .toList();
    }

    public List<OrderResponse> from(List<OrderModel> orders) {
        return orders.stream()
                .map(OrderResponse::new)
                .toList();
    }
}

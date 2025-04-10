package br.com.four.ecom.core.infrastructure.database.entities;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.CreateOrderModel;
import br.com.four.ecom.core.domains.orders.models.CreatedOrderModel;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.models.OrderProductsModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@IdClass(OrderId.class)
public class Order {

    @Id
    @Column(name = "order_id", nullable = false, length = 64)
    private String orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "status")
    private String status;

    @Id
    @Column(name = "product_id", nullable = false, length = 64)
    private String productId;

    @Id
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public static Order newOrder(CreateOrderModel orderModel, Double price, String productId) {
        return Order.builder()
                .orderId(UUID.randomUUID().toString())
                .customerId(orderModel.getCustomerId())
                .status("OPEN")
                .productId(productId)
                .price(price)
                .quantity(orderModel.getQuantity())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public CreatedOrderModel toCreatedOrderModel() {
        return CreatedOrderModel.builder()
                .orderId(this.orderId)
                .customerId(this.customerId)
                .status(this.status)
                .productId(this.productId)
                .price(this.price)
                .quantity(this.quantity)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static Order fromOrderModel(OrderModel orderModel) {
        OrderProductsModel product = orderModel.getProducts().getFirst();

        return Order.builder()
                .orderId(orderModel.getOrderId())
                .customerId(orderModel.getCustomerId())
                .status(orderModel.getStatus().name())
                .productId(product.getProductId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(orderModel.getCreatedAt())
                .updatedAt(orderModel.getUpdatedAt())
                .build();
    }

    private Order createNewOrderEntry(OrderModel existingOrder, Product product, OrderInput orderInput) {
        return Order.builder()
                .orderId(existingOrder.getOrderId())
                .productId(product.getId())
                .price(product.getPrice())
                .quantity(orderInput.getProduct().getQuantity())
                .customerId(existingOrder.getCustomerId())
                .status(existingOrder.getStatus().name())
                .createdAt(existingOrder.getCreatedAt())
                .updatedAt(existingOrder.getUpdatedAt())
                .build();
    }
}

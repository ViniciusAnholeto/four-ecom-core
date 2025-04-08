package br.com.four.ecom.core.domains.orders.strategies.status;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.models.OrderProductsModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderStatusOpen implements HasStatus {

    private final DatabasePort databasePort;

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.OPEN;
    }

    @Override
    public OrderModel execute(OrderModel existingOrder, OrderInput orderInput) {
        List<OrderProductsModel> updatedProducts = new ArrayList<>();
        for (OrderProductsModel product : existingOrder.getProducts()) {
            if (product.getProductId().equals(orderInput.getProduct().getProductId())) {
                product.setQuantity(orderInput.getProduct().getQuantity());
            }
            updatedProducts.add(product);
        }

        existingOrder.setProducts(updatedProducts);
        existingOrder.setStatus(OrderStatusEnum.OPEN);

        Optional<OrderModel> updatedOrder = databasePort.updateOrder(existingOrder);

        return updatedOrder.orElseThrow(() -> new RuntimeException("Failed to update order"));
    }
}

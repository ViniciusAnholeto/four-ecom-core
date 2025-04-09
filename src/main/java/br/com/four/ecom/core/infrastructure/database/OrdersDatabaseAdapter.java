package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrdersDatabaseAdapter implements DatabasePort {


    @Override
    public Optional<OrderModel> getOrderById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderModel>> getOrderByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderModel> createOrder(OrderModel orderModel) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderModel> updateOrder(OrderModel orderModel) {
        return Optional.empty();
    }

    @Override
    public void updateProductQuantity(UUID productId, Integer quantity) {

    }
}

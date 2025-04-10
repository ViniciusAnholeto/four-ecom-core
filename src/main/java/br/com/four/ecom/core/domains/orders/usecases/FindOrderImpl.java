package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundForCustomerIdException;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import br.com.four.ecom.core.domains.orders.resources.FindOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindOrderImpl implements FindOrder {

    private final DatabasePort databasePort;

    @Override
    public OrderModel executeById(String id) {
        Optional<OrderModel> order = databasePort.getOrderById(id);

        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundException(id);
        }
    }

    @Override
    public List<OrderModel> executeByCustomerId(String customerId) {
        Optional<List<OrderModel>> order = databasePort.getOrderByCustomerId(customerId);

        if (order.isPresent() && !order.get().isEmpty()) {
            return order.get();
        } else {
            throw new OrderNotFoundForCustomerIdException(customerId);
        }
    }
}

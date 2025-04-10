package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.models.OrderModel;

import java.util.List;

public interface FindOrder {
    OrderModel executeById(String id);

    List<OrderModel> executeByCustomerId(String customerId);
}

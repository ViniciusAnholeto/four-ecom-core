package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.models.OrderModel;

import java.util.List;

public interface FindOrder {
    OrderModel executeById(Long id);

    List<OrderModel> executeByUserId(Long userId);
}

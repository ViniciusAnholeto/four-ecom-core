package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.models.OrderModel;

public interface FindOrder {
    OrderModel executeById(Long id);

    OrderModel executeByUserCode(Long userId);
}

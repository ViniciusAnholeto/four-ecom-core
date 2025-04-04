package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.resources.FindOrder;

public class FindOrderImpl implements FindOrder {

    @Override
    public OrderModel executeById(Long id) {
        // Logic to find order by ID
        return new OrderModel();
    }

    @Override
    public OrderModel executeByUserCode(Long userId) {
        // Logic to find order by user ID
        return new OrderModel();
    }
}

package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.resources.DeleteOrder;

public class DeleteOrderImpl implements DeleteOrder {

    @Override
    public void execute(Long id) {
        // Logic to delete order by ID
        // This could involve calling a repository method to delete the order from the database
        System.out.println("Order with ID " + id + " has been deleted.");
    }
}

package br.com.four.ecom.core.infrastructure.database.repositories;

import java.util.ArrayList;

public class OrdersRepository {

    //crie aqui uma consulta a um banco de dados MySql
    //para buscar os pedidos de um cliente, utilizando o id do pedido

    public List<Order> findOrdersByCustomerId(Long customerId) {
        // Implementar a lógica de consulta ao banco de dados

        return new ArrayList<>();
    }

}

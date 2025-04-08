package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import br.com.four.ecom.core.domains.orders.ports.KafkaPort;
import br.com.four.ecom.core.domains.orders.resources.CancelOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancelOrderImpl implements CancelOrder {

    private final KafkaPort kafkaPort;
    private final DatabasePort databasePort;

    @Override
    public void execute(Long id) {
        databasePort.getOrderById(id).orElseThrow(() -> new OrderNotFoundException(id));
        cancelOrder(id);
    }

    private void cancelOrder(Long id) {
        kafkaPort.sendCancelCommand(id);
    }
}

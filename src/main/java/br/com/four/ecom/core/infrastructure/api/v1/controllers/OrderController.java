package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.orders.resources.CreateOrUpdateOrder;
import br.com.four.ecom.core.domains.orders.resources.DeleteOrder;
import br.com.four.ecom.core.domains.orders.resources.FindOrder;
import br.com.four.ecom.core.domains.orders.resources.PayOrder;
import br.com.four.ecom.core.infrastructure.api.v1.request.OrderRequest;
import br.com.four.ecom.core.infrastructure.api.v1.request.PaymentRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.OrderResponse;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.OrderControllerDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ecom/v1")
@RequiredArgsConstructor
public class OrderController implements OrderControllerDoc {

    private final CreateOrUpdateOrder createOrUpdateOrder;
    private final FindOrder findOrder;
    private final DeleteOrder deleteOrder;
    private final PayOrder payOrder;

    @Override
    @PostMapping("/order")
    public OrderResponse createOrUpdateOrder(@RequestBody OrderRequest orderRequest) {

        log.info("Creating or updating order: {}", orderRequest);

        return new OrderResponse(createOrUpdateOrder.execute(orderRequest.toInput()));
    }

    @Override
    @PostMapping("/order/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {

        log.info("Finding order with id: {}", id);

        return new OrderResponse(findOrder.executeById(id));
    }

    @Override
    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id) {

        log.info("Deleting order with id: {}", id);

        deleteOrder.execute(id);
    }

    @Override
    @GetMapping("/order/user/{user}")
    public OrderResponse getOrderByUserId(@PathVariable Long user) {

        log.info("Finding order for user with id: {}", user);

        return new OrderResponse(findOrder.executeByUserCode(user));
    }

    @Override
    @PostMapping("/order/payment/{id}")
    public OrderResponse paymentOrder(@PathVariable Long id,
                                      @RequestBody PaymentRequest paymentRequest) {

        log.info("Processing payment for order with id: {}", id);

        return new OrderResponse(payOrder.execute(id, paymentRequest.toInput()));
    }
}

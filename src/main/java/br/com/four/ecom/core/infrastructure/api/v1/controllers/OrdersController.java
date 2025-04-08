package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.orders.resources.CreateOrUpdateOrder;
import br.com.four.ecom.core.domains.orders.resources.CancelOrder;
import br.com.four.ecom.core.domains.orders.resources.FindOrder;
import br.com.four.ecom.core.domains.orders.resources.PayOrder;
import br.com.four.ecom.core.infrastructure.api.v1.request.OrderRequest;
import br.com.four.ecom.core.infrastructure.api.v1.request.PaymentRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.OrderResponse;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.OrdersControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/v1")
public class OrdersController implements OrdersControllerDoc {

    private CreateOrUpdateOrder createOrUpdateOrder;
    private FindOrder findOrder;
    private CancelOrder cancelOrder;
    private PayOrder payOrder;

    @Override
    @PostMapping("/order")
    public OrderResponse createOrUpdateOrder(@RequestBody @Valid OrderRequest orderRequest) {

        log.info("Creating or updating order: {}", orderRequest);

        return new OrderResponse(createOrUpdateOrder.execute(orderRequest.toInput()));
    }

    @Override
    @GetMapping("/order/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {

        log.info("Finding order with id: {}", id);

        return new OrderResponse(findOrder.executeById(id));
    }

    @Override
    @GetMapping("/order/user/{user}")
    public List<OrderResponse> getOrderByUserId(@PathVariable Long user) {

        log.info("Finding order for user with id: {}", user);

        return new OrderResponse().from(findOrder.executeByUserId(user));
    }

    @Override
    @DeleteMapping("/order/{id}")
    public void cancelOrder(@PathVariable Long id) {

        log.info("Canceling order with id: {}", id);

        cancelOrder.execute(id);
    }

    @Override
    @PostMapping("/order/payment/{id}")
    public OrderResponse paymentOrder(@PathVariable Long id,
                                      @RequestBody @Valid PaymentRequest paymentRequest) {

        log.info("Processing payment for order with id: {}", id);

        return new OrderResponse(payOrder.execute(id, paymentRequest.toInput()));
    }
}

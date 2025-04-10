package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.orders.enums.OrderOperationEnum;
import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.ProductNotFoundException;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.*;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import br.com.four.ecom.core.infrastructure.database.entities.Order;
import br.com.four.ecom.core.infrastructure.database.entities.Product;
import br.com.four.ecom.core.infrastructure.database.repositories.OrdersRepository;
import br.com.four.ecom.core.infrastructure.database.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrdersDatabaseAdapter implements DatabasePort {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Override
    public CreatedOrderModel createOrder(CreateOrderModel orderModel) {
        log.info("Creating order: {}", orderModel);

        Optional<Product> product = productRepository.findById(orderModel.getProductId());
        if (product.isEmpty()) {
            log.error("Product not found with ID: {}", orderModel.getProductId());
            throw new ProductNotFoundException(orderModel.getProductId());
        }

        return ordersRepository.save(Order.newOrder(
                orderModel, product.get().getPrice(), product.get().getId())).toCreatedOrderModel();
    }


    @Override
    public Optional<OrderModel> getOrderById(String id) {
        log.info("Finding order by ID: {}", id);

        List<Order> orders = ordersRepository.findAllByOrderId(id);

        if (orders.isEmpty()) {
            log.warn("Order not found for ID: {}", id);
            return Optional.empty();
        }

        List<OrderProductsModel> orderProducts = buildListOrderProductsModel(orders);

        Order firstOrder = orders.getFirst();

        return Optional.of(OrderModel.builder()
                .orderId(firstOrder.getOrderId())
                .customerId(firstOrder.getCustomerId())
                .status(OrderStatusEnum.valueOf(firstOrder.getStatus()))
                .products(orderProducts)
                .totalPrice(totalPrice(orderProducts))
                .createdAt(firstOrder.getCreatedAt())
                .updatedAt(firstOrder.getUpdatedAt())
                .build());
    }

    @Override
    public Optional<List<OrderModel>> getOrderByCustomerId(String customerId) {
        log.info("Finding orders by customer ID: {}", customerId);

        List<Order> orders = ordersRepository.findAllByCustomerId(customerId);

        if (orders.isEmpty()) {
            log.warn("No orders found for customer ID: {}", customerId);
            return Optional.empty();
        }

        Map<String, List<Order>> ordersGroupedByOrderId = orders.stream()
                .collect(Collectors.groupingBy(Order::getOrderId));

        List<OrderModel> orderModels = ordersGroupedByOrderId.entrySet().stream()
                .map(entry -> {
                    String orderId = entry.getKey();
                    List<Order> orderGroup = entry.getValue();

                    List<OrderProductsModel> orderProducts = buildListOrderProductsModel(orderGroup);

                    Order firstOrder = orderGroup.getFirst();

                    return OrderModel.builder()
                            .orderId(orderId)
                            .customerId(firstOrder.getCustomerId())
                            .status(OrderStatusEnum.valueOf(firstOrder.getStatus()))
                            .products(orderProducts)
                            .totalPrice(totalPrice(orderProducts))
                            .createdAt(firstOrder.getCreatedAt())
                            .updatedAt(firstOrder.getUpdatedAt())
                            .build();
                })
                .toList();

        return Optional.of(orderModels);
    }

    @Override
    public Optional<OrderModel> updateOrder(OrderModel existingOrder,  OrderInput orderInput) {
        log.info("Updating order: {} with operation: {}", orderInput.getOrderId(), orderInput.getOperation());

        String operationToExecute = orderInput.getOperation().name();

        if (OrderOperationEnum.ADD.getOperation().equals(operationToExecute)) {
            handleAddProductToOrder(existingOrder, orderInput);
        }

        else if (OrderOperationEnum.REMOVE.getOperation().equals(operationToExecute)) {
            handleRemoveProductFromOrder(existingOrder, orderInput);
        }

        ordersRepository.save(Order.fromOrderModel(existingOrder));
        return getOrderById(existingOrder.getOrderId());
    }

    @Override
    public void updateProductDatabase(String productId, Integer quantity) {

        //esse método vai atualizar a base de produtos e o elasticsearch

    }

    @Override
    public Optional<OrderModel> updateOrderStatus(String orderId, String status) {
        log.info("Updating order status: {} to {}", orderId, status);

        List<Order> orders = ordersRepository.findAllByOrderId(orderId);

        if (orders.isEmpty()) {
            log.warn("No orders found for ID: {}", orderId);
            return Optional.empty();
        }

        orders.forEach(order -> {
            order.setStatus(status);
            ordersRepository.save(order);
        });

        return getOrderById(orderId);
    }

    private void handleAddProductToOrder(OrderModel existingOrder, OrderInput orderInput) {
        String productIdToUpdate = orderInput.getProduct().getProductId();

        Optional<Product> productOptional = productRepository.findById(productIdToUpdate);
        if (productOptional.isEmpty()) {
            log.error("Product not found to ADD with ID: {}", productIdToUpdate);
            throw new ProductNotFoundException(productIdToUpdate);
        }

        Product product = productOptional.get();

        Optional<OrderProductsModel> existingProduct = existingOrder.getProducts().stream()
                .filter(p -> p.getProductId().equals(productIdToUpdate) && p.getPrice().equals(product.getPrice()))
                .findFirst();

        if (existingProduct.isPresent()) {
            OrderProductsModel productToUpdate = existingProduct.get();
            int updatedQuantity = productToUpdate.getQuantity() + orderInput.getProduct().getQuantity();
            productToUpdate.setQuantity(updatedQuantity);

            existingOrder.setProducts(new ArrayList<>());
            existingOrder.getProducts().add(productToUpdate);
        } else {
            Order newOrderEntry = new Order();
            newOrderEntry.setOrderId(existingOrder.getOrderId());
            newOrderEntry.setProductId(product.getId());
            newOrderEntry.setPrice(product.getPrice());
            newOrderEntry.setQuantity(orderInput.getProduct().getQuantity());
            newOrderEntry.setCustomerId(existingOrder.getCustomerId());
            newOrderEntry.setStatus(existingOrder.getStatus().name());
            newOrderEntry.setCreatedAt(existingOrder.getCreatedAt());
            newOrderEntry.setUpdatedAt(existingOrder.getUpdatedAt());

            ordersRepository.save(newOrderEntry);
        }
    }

    private void handleRemoveProductFromOrder(OrderModel existingOrder, OrderInput orderInput) {
        String productIdToUpdate = orderInput.getProduct().getProductId();

        for (OrderProductsModel product : existingOrder.getProducts()) {
            if (productIdToUpdate.equals(product.getProductId())) {
                int updatedQuantity = product.getQuantity() - orderInput.getProduct().getQuantity();

                if (updatedQuantity <= 0) {
                    Order orderToDelete = ordersRepository.findByOrderIdAndProductId(
                            existingOrder.getOrderId(), productIdToUpdate).orElseThrow(() ->
                            new OrderNotFoundException(existingOrder.getOrderId()));
                    ordersRepository.delete(orderToDelete);
                    log.info("Product removed from order: {}", productIdToUpdate);
                    break;
                }

                product.setQuantity(updatedQuantity);
            }
            existingOrder.setProducts(new ArrayList<>());
            existingOrder.getProducts().add(product);
        }
    }

    private double totalPrice(List<OrderProductsModel> orderProducts) {
        return orderProducts.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    private List<OrderProductsModel> buildListOrderProductsModel(List<Order> orders) {
        return orders.stream()
                .map(order -> OrderProductsModel.builder()
                        .productId(order.getProductId())
                        .price(order.getPrice())
                        .quantity(order.getQuantity())
                        .build())
                .toList();
    }

}

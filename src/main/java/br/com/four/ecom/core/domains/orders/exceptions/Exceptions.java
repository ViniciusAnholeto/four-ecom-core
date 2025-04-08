package br.com.four.ecom.core.domains.orders.exceptions;

import br.com.four.ecom.core.domains.commons.EcomException.SynchronousException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exceptions {

    public static class OrderNotFoundException extends SynchronousException {

        public OrderNotFoundException(Long orderId) {
            super("ECOM-DO-001", String.format("Order ID: %d not found.", orderId));
        }
    }

    public static class OrderNotFoundForUserIdException extends SynchronousException {

        public OrderNotFoundForUserIdException(Long userId) {
            super("ECOM-DO-002", String.format("Order not found for user id: %d.", userId));
        }
    }

    public static class OrderCreationFailedException extends SynchronousException {

        public OrderCreationFailedException() {
            super("ECOM-DO-003", "Order creation failed.");
        }
    }

    public static class InvalidOrderStatusException extends SynchronousException {

        public InvalidOrderStatusException(String orderStatus, Long orderId) {
            super("ECOM-DO-004", String.format("Cannot update order id %d with status %s.", orderId, orderStatus));
        }
    }

    public static class OrderCancelFailedException extends SynchronousException {

        public OrderCancelFailedException(Long orderId, String orderStatus) {
            super("ECOM-DO-005", String.format("Cannot cancel order id %d with status %s.", orderId, orderStatus));
        }
    }

    public static class OrderPaymentFailedException extends SynchronousException {

        public OrderPaymentFailedException(Long orderId) {
            super("ECOM-DO-006", String.format("Order status update failed for order id %d.", orderId));
        }
    }

}

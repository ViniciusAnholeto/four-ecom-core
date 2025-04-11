package br.com.four.ecom.core.domains.orders.exceptions;

import br.com.four.ecom.core.domains.commons.EcomException.SynchronousException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exceptions {

    public static class OrderNotFoundException extends SynchronousException {

        public OrderNotFoundException(String orderId) {
            super("ECOM-DO-001", String.format("Order id: %s not found.", orderId));
        }
    }

    public static class OrderNotFoundForCustomerIdException extends SynchronousException {

        public OrderNotFoundForCustomerIdException(String customerId) {
            super("ECOM-DO-002", String.format("Order not found for customer id: %s.", customerId));
        }
    }

    public static class OrderCreationFailedException extends SynchronousException {

        public OrderCreationFailedException() {
            super("ECOM-DO-003", "Order creation failed.");
        }
    }

    public static class InvalidOrderStatusException extends SynchronousException {

        public InvalidOrderStatusException(String orderStatus, String orderId) {
            super("ECOM-DO-004", String.format("Cannot update order id: %s with status: %s.", orderId, orderStatus));
        }
    }

    public static class OrderCancelFailedException extends SynchronousException {

        public OrderCancelFailedException(String orderId, String orderStatus) {
            super("ECOM-DO-005", String.format("Cannot cancel order id: %s with status: %s.", orderId, orderStatus));
        }
    }

    public static class OrderPaymentFailedException extends SynchronousException {

        public OrderPaymentFailedException(String orderId) {
            super("ECOM-DO-006", String.format("Order status update failed for order id: %s.", orderId));
        }
    }

    public static class ProductNotFoundException extends SynchronousException {

        public ProductNotFoundException(String productId) {
            super("ECOM-DO-007", String.format("Product UUID: %s not found.", productId));
        }
    }

    public static class InvalidCommandException extends SynchronousException {

        public InvalidCommandException(String command, String orderId) {
            super("ECOM-DO-008", String.format("Invalid command: %s for order id: %s", command, orderId));
        }
    }

}

package br.com.four.ecom.core.domains.products.exceptions;

import br.com.four.ecom.core.utils.EcomException.SynchronousException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exceptions {

  public static class ProductNotFoundException extends SynchronousException {

    public ProductNotFoundException(String productId) {
      super("ECOM-DP-001", String.format("Product UUID: %s not found.", productId));
    }

    public ProductNotFoundException(Object parameters) {
      super("ECOM-DP-002", String.format("Product not found with parameters: {}", parameters));
    }
  }
}

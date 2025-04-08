package br.com.four.ecom.core.domains.products.resources;

import java.util.UUID;

public interface DeleteProduct {
    void execute(UUID id);
}

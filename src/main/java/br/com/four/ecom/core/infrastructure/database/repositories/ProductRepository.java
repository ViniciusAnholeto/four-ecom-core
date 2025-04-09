package br.com.four.ecom.core.infrastructure.database.repositories;

import br.com.four.ecom.core.infrastructure.database.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}

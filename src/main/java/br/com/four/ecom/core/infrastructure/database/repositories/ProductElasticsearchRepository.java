package br.com.four.ecom.core.infrastructure.database.repositories;

import br.com.four.ecom.core.infrastructure.database.entities.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductDocument, String> {
}

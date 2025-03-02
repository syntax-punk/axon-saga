package no.syntaxpunk.cqrsestore.ProductService.core.data;

import no.syntaxpunk.cqrsestore.ProductService.core.data.entity.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}

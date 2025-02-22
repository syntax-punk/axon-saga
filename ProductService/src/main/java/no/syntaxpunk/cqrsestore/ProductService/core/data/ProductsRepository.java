package no.syntaxpunk.cqrsestore.ProductService.core.data;

import no.syntaxpunk.cqrsestore.ProductService.core.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
//    ProductEntity findByProductId(String productId);
//    ProductEntity finByProductIdOrTitle(String productId, String title);
}

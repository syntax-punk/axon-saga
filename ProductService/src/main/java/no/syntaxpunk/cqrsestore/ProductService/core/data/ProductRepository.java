package no.syntaxpunk.cqrsestore.ProductService.core.data;

import no.syntaxpunk.cqrsestore.ProductService.core.data.entity.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDto, String> {
}

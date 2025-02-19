package no.syntaxpunk.cqrsestore.ProductService.db;

import no.syntaxpunk.cqrsestore.ProductService.entity.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDto, String> {
}

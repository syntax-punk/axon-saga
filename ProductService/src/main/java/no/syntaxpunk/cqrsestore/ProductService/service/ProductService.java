package no.syntaxpunk.cqrsestore.ProductService.service;

import no.syntaxpunk.cqrsestore.ProductService.command.CreateProductCommand;
import no.syntaxpunk.cqrsestore.ProductService.core.events.ProductCreatedEvent;
import no.syntaxpunk.cqrsestore.ProductService.db.ProductRepository;
import no.syntaxpunk.cqrsestore.ProductService.entity.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductCommand createProductCommand) {
        var productDto = new ProductDto();
        BeanUtils.copyProperties(createProductCommand, productDto);

        productRepository.save(productDto);
    }

    public ProductDto getProduct(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll();
    }
}

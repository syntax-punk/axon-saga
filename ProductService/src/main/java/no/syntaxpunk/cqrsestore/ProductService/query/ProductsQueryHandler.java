package no.syntaxpunk.cqrsestore.ProductService.query;

import no.syntaxpunk.cqrsestore.ProductService.core.data.ProductsRepository;
import no.syntaxpunk.cqrsestore.ProductService.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsQueryHandler {

    private final ProductsRepository productsRepository;

    public ProductsQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductRestModel> products = new ArrayList<>();
        var productEntities = productsRepository.findAll();

        for (var entity : productEntities) {
            var product = new ProductRestModel();
            BeanUtils.copyProperties(entity, product);
            products.add(product);
        }

        return  products;
    }
}

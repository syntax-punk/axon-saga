package no.syntaxpunk.cqrsestore.ProductService.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    Environment env;

    @GetMapping
    public String getProducts() {
        return "getting products: " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        return "product created: " + createProductRestModel.getTitle();
    }
}

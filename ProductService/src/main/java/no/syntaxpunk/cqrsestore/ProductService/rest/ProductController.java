package no.syntaxpunk.cqrsestore.ProductService.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String createProduct() {
        return "product created";
    }
}

package no.syntaxpunk.cqrsestore.ProductService.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public String createProduct() {
        return "product created";
    }
}

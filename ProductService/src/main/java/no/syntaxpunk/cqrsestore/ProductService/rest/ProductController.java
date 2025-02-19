package no.syntaxpunk.cqrsestore.ProductService.rest;

import no.syntaxpunk.cqrsestore.ProductService.command.CreateProductCommand;
import no.syntaxpunk.cqrsestore.ProductService.service.ProductService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Environment env;
    private final CommandGateway commandGateway;
    private final ProductService productService;

    public ProductController(Environment env, CommandGateway commandGateway, ProductService productService) {
        this.env = env;
        this.commandGateway = commandGateway;
        this.productService = productService;
    }

    @GetMapping
    public String getProducts() {
        return "getting products: " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        var createProductCommand = CreateProductCommand.builder()
            .productId(UUID.randomUUID().toString())
            .title(createProductRestModel.getTitle())
            .price(createProductRestModel.getPrice())
            .quantity(createProductRestModel.getQuantity())
            .build();

        String returnValue;

        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);

            this.productService.createProduct(createProductCommand);
        } catch (Exception ex) {
            returnValue = ex.getLocalizedMessage();
        }

        return returnValue;
    }
}

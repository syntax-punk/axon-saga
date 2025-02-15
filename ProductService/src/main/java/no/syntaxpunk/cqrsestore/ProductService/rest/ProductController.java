package no.syntaxpunk.cqrsestore.ProductService.rest;

import no.syntaxpunk.cqrsestore.ProductService.command.CreateProductCommand;
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

    public ProductController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @GetMapping
    public String getProducts() {
        return "getting products: " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
        var createProductCommand = CreateProductCommand.builder()
            .title(createProductRestModel.getTitle())
            .price(createProductRestModel.getPrice())
            .quantity(createProductRestModel.getQuantity())
            .productId(UUID.randomUUID().toString())
            .build();

        String returnValue;
        
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            returnValue = ex.getLocalizedMessage();
        }

        return returnValue;
    }
}

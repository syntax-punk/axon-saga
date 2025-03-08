package no.syntaxpunk.cqrsestore.ProductService.command.rest;

import jakarta.validation.Valid;
import no.syntaxpunk.cqrsestore.ProductService.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        var createProductCommand = CreateProductCommand.builder()
            .productId(UUID.randomUUID().toString())
            .title(createProductRestModel.getTitle())
            .price(createProductRestModel.getPrice())
            .quantity(createProductRestModel.getQuantity())
            .build();

        String returnValue = commandGateway.sendAndWait(createProductCommand);

        return returnValue;
    }
}

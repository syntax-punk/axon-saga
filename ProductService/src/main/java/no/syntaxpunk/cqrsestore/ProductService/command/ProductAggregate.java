package no.syntaxpunk.cqrsestore.ProductService.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    public ProductAggregate() {}

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // Validate createProductCommand
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be less than or equal to zero");
        }

        if (createProductCommand.getTitle() == null
                || createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

}

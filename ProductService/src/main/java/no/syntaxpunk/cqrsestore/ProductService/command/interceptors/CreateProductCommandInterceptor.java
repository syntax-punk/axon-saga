package no.syntaxpunk.cqrsestore.ProductService.command.interceptors;

import no.syntaxpunk.cqrsestore.ProductService.command.CreateProductCommand;
import no.syntaxpunk.cqrsestore.ProductService.core.data.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>>
{
    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }
    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            logger.info("-> intercepted command: {}", command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                // Validate createProductCommand
                var createProductCommand = (CreateProductCommand) command;

                var productLookupEntity =
                        productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
                if (productLookupEntity != null) {
                    throw new IllegalStateException(String.format("Product with an id %s or title %s already exists",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
                }
            }

            return command;
        };
    }
}

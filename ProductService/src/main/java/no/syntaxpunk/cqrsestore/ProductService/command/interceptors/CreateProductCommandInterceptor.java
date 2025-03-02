package no.syntaxpunk.cqrsestore.ProductService.command.interceptors;

import no.syntaxpunk.cqrsestore.ProductService.command.CreateProductCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>>
{

    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            logger.info("-> intercepted command: {}", command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                // Validate createProductCommand
                var createProductCommand = (CreateProductCommand) command;

                if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price cannot be less than or equal to zero");
                }

                if (createProductCommand.getTitle() == null
                        || createProductCommand.getTitle().isBlank()) {
                    throw new IllegalArgumentException("Title cannot be empty");
                }
            }

            return command;
        };
    }
}

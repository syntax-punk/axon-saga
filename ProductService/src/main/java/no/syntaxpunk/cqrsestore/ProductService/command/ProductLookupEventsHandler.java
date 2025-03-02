package no.syntaxpunk.cqrsestore.ProductService.command;

import no.syntaxpunk.cqrsestore.ProductService.core.data.ProductLookupRepository;
import no.syntaxpunk.cqrsestore.ProductService.core.data.entity.ProductLookupEntity;
import no.syntaxpunk.cqrsestore.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        var entity = new ProductLookupEntity(event.getProductId(), event.getTitle());
        
        this.productLookupRepository.save(entity);
    }
}

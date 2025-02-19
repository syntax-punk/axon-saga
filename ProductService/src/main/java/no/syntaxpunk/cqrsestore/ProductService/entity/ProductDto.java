package no.syntaxpunk.cqrsestore.ProductService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductDto {
    @Id
    private String productId;
    private String title;
    private double price;
    private int quantity;
}

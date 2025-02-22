package no.syntaxpunk.cqrsestore.ProductService.core.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
public class ProductDto implements Serializable {
    @Id
    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;
    private double price;
    private int quantity;
}

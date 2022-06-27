package mk.ukim.finki.rapshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Artist artist;

    public Product() {
    }

    public Product(String name, Double price, Integer quantity, Category category, Artist artist) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.artist = artist;
    }
}
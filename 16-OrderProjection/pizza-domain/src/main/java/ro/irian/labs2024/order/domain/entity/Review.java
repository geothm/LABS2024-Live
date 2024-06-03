package ro.irian.labs2024.order.domain.entity;

import jakarta.persistence.*;
import ro.irian.labs2024.pizza.domain.entity.Pizza;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(Customer customer, Pizza pizza, Integer rating, String comment) {
        this.customer = customer;
        this.pizza = pizza;
        this.rating = rating;
        this.comment = comment;
    }

    @PrePersist
    void prePersist() {
        date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public boolean isTransient() {
        return version == null;
    }

}

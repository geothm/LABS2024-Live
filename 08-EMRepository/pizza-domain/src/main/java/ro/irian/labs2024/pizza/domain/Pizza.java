package ro.irian.labs2024.pizza.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public Pizza() {
    }

    public Pizza(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pizza(Long id, String name, Double price, LocalDateTime addedOn) {
        this(id, name);
        this.price = price;
        this.addedOn = addedOn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public boolean nameContains(String nameContains) {
        return name.toLowerCase()
                .contains(nameContains.toLowerCase());
    }

    public Long getVersion() {
        return version;
    }

    public boolean isTransient() {
        return version == null;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(id, pizza.id) && Objects.equals(name, pizza.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

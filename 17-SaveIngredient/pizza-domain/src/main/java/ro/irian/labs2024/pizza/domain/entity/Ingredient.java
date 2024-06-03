package ro.irian.labs2024.pizza.domain.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Embedded
    private CurrencyValue price;

    @Version
    private Long version;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, CurrencyValue price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(CurrencyValue price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CurrencyValue getPrice() {
        return price;
    }

    public boolean isTransient() {
        return version == null;
    }
}

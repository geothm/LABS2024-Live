package ro.irian.labs2024.pizza.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> allergens;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    public Pizza() {
    }

    public Pizza(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pizza(Long id, String name, Double price, LocalDateTime addedOn, List<String> allergens) {
        this(id, name);
        this.price = price;
        this.addedOn = addedOn;
        this.allergens = allergens;
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

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isTransient() {
        return version == null;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", addedOn=" + addedOn +
                ", version=" + version +
                ", allergens=" + allergens +
                ", ingredients=" + ingredients +
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

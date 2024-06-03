package ro.irian.labs2024.order.domain.entity;

import jakarta.persistence.*;
import ro.irian.labs2024.pizza.domain.entity.CurrencyValue;
import ro.irian.labs2024.order.domain.entity.Customer;
import ro.irian.labs2024.pizza.domain.entity.Pizza;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "total_value"/*, nullable = false*/))
    @AttributeOverride(name = "price_currency", column = @Column(name = "currency"/*, nullable = false*/))
    private CurrencyValue price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzaList;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CurrencyValue getPrice() {
        return price;
    }

    public void setPrice(CurrencyValue price) {
        this.price = price;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public boolean isTransient() {
        return version == null;
    }

}

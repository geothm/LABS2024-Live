package ro.irian.labs2024.pizza.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PizzaOrder pizzaOrder) {
        if (pizzaOrder.isTransient()) {
            em.persist(pizzaOrder);
        }
        else {
            em.merge(pizzaOrder);
        }
    }

    public List<PizzaOrder> findAll() {
        return em.createQuery("SELECT o FROM PizzaOrder o", PizzaOrder.class).getResultList();
    }
}

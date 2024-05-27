package ro.irian.labs2024.order.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ro.irian.labs2024.order.domain.entity.PizzaOrder;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public PizzaOrder findById(Long id) {
        return em.find(PizzaOrder.class, id);
    }

    public void save(PizzaOrder pizzaOrder) {
        if (pizzaOrder.isTransient()) {
            em.persist(pizzaOrder);
        }
        else {
            em.merge(pizzaOrder);
        }
    }

    public void delete(PizzaOrder pizzaOrder) {
        em.remove(pizzaOrder);
    }

    public List<PizzaOrder> findAll() {
        return em.createQuery("SELECT o FROM PizzaOrder o", PizzaOrder.class).getResultList();
    }

    /*
    public List<Pizza> findNameContains(String nameContains) {
        return em.createQuery("SELECT p FROM Pizza p WHERE lower(p.name) LIKE lower(:nameContains)", Pizza.class)
                .setParameter("nameContains", "%" + nameContains + "%")
                .getResultList();
    }*/

}

package ro.irian.labs2024.pizza.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Customer customer) {
        if (customer.isTransient()) {
            em.persist(customer);
        }
        else {
            em.merge(customer);
        }
    }

    public void delete(Customer customer) {
        em.remove(customer);
    }
}

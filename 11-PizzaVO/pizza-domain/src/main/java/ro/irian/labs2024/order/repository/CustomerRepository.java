package ro.irian.labs2024.order.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ro.irian.labs2024.order.domain.entity.Customer;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

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

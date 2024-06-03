package ro.irian.labs2024.order.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ro.irian.labs2024.order.domain.entity.Customer;
import ro.irian.labs2024.order.domain.vo.CustomerVO;
import ro.irian.labs2024.order.domain.vo.OrderVO;

import java.util.List;

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

    public List<CustomerVO> getCustomerVOs(){
        return em.createQuery("""
                                SELECT new ro.irian.labs2024.order.domain.vo.CustomerVO(customer.id, customer.name,
                                                                                        sum(pizzaOrder.price.value),
                                                                                        pizzaOrder.price.currency)
                                FROM Customer customer
                                LEFT JOIN customer.pizzaOrders pizzaOrder
                                GROUP BY pizzaOrder.customer, pizzaOrder.price.currency                                                                                             
                            """, CustomerVO.class)
                .getResultList();
    }
}

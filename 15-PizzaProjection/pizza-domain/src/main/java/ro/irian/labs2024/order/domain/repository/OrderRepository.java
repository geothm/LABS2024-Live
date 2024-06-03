package ro.irian.labs2024.order.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ro.irian.labs2024.order.domain.entity.PizzaOrder;
import ro.irian.labs2024.order.domain.vo.OrderVO;
import ro.irian.labs2024.pizza.domain.vo.PizzaVO;

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

    public List<OrderVO> getOrderVOs(){
        return em.createQuery("""
                                SELECT new ro.irian.labs2024.order.domain.vo.OrderVO(order.id, customer.name, order.date, count(pizza))
                                FROM PizzaOrder order
                                LEFT JOIN order.customer customer  
                                LEFT JOIN order.pizzaList pizza  
                                GROUP BY order.id                                                                                             
                            """, OrderVO.class)
                .getResultList();
    }

}

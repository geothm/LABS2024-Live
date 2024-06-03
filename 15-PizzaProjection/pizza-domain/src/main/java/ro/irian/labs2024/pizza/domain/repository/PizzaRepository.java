package ro.irian.labs2024.pizza.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ro.irian.labs2024.pizza.domain.entity.Pizza;
import ro.irian.labs2024.pizza.domain.vo.PizzaVO;

import java.util.List;

@Repository
public class PizzaRepository {

    @PersistenceContext
    private EntityManager em;

    public Pizza findById(Long id) {
        return em.find(Pizza.class, id);
    }

    public void save(Pizza pizza) {
        if (pizza.isTransient()) {
            em.persist(pizza);
        }
        else {
            em.merge(pizza);
        }
    }

    public void delete(Pizza pizza) {
        em.remove(pizza);
    }

    public List<Pizza> findAll() {
        return em.createQuery("SELECT p FROM Pizza p", Pizza.class).getResultList();
    }

    public List<Pizza> findNameContains(String nameContains) {
        return em.createQuery("SELECT p FROM Pizza p WHERE lower(p.name) LIKE lower(:nameContains)", Pizza.class)
                .setParameter("nameContains", "%" + nameContains + "%")
                .getResultList();
    }

    public List<Pizza> findByIngredientName(String ingredient) {
        return em.createQuery("""
                        SELECT p FROM Pizza p
                        JOIN p.ingredients i
                        WHERE lower(i.name) LIKE lower(:ingredientName)
                    """, Pizza.class)
                .setParameter("ingredientName", ingredient)
                .getResultList();
    }

    public List<PizzaVO> getPizzaVOs(){
        return em.createQuery("""
                                SELECT new ro.irian.labs2024.pizza.domain.vo.PizzaVO(p.id, p.name, count(i)) 
                                FROM Pizza p   
                                LEFT JOIN p.ingredients i  
                                GROUP BY p.id, p.name                                                                                               
                            """, PizzaVO.class)
                .getResultList();
    }

}

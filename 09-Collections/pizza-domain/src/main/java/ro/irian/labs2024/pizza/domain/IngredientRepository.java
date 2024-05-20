package ro.irian.labs2024.pizza.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientRepository {

    @PersistenceContext
    private EntityManager em;

    public Ingredient findById(Long id) {
        return em.find(Ingredient.class, id);
    }

    public void save(Ingredient ingredient) {
        if (ingredient.isTransient()) {
            em.persist(ingredient);
        }
        else {
            em.merge(ingredient);
        }
    }

    public void delete(Ingredient ingredient) {
        em.remove(ingredient);
    }

    public List<Ingredient> findAll() {
        return em.createQuery("SELECT ingredients FROM Ingredient ingredients", Ingredient.class).getResultList();
    }

    public Ingredient findByName(String name) {
        return em.createQuery("SELECT i FROM Ingredient i WHERE lower(i.name) = lower(:name)", Ingredient.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

}

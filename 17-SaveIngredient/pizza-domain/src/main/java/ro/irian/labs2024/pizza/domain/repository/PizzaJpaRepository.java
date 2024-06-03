package ro.irian.labs2024.pizza.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.irian.labs2024.pizza.domain.entity.Pizza;
import ro.irian.labs2024.pizza.domain.vo.PizzaProjection;
import ro.irian.labs2024.pizza.domain.vo.PizzaVO;

import java.util.List;

public interface PizzaJpaRepository extends JpaRepository<Pizza, Long> {

    Page<Pizza> findAllByNameEqualsIgnoreCase(String name, Pageable pageable);

    @Query("""
            SELECT new ro.irian.labs2024.pizza.domain.vo.PizzaVO(p.id, p.name, count(i)) 
            FROM Pizza p   
            LEFT JOIN p.ingredients i  
            GROUP BY p.id, p.name                                                                                               
        """)
    List<PizzaVO> findAllPizzaVOs();

    @Query("""
            SELECT new ro.irian.labs2024.pizza.domain.vo.PizzaVO(p.id, p.name, count(i)) 
            FROM Pizza p   
            LEFT JOIN p.ingredients i  
            WHERE lower(p.name) LIKE lower(:name)
            GROUP BY p.id, p.name                                                                                               
        """)
    List<PizzaVO> findAllPizzaVOsByName(@Param("name") String name);

    /*@Query("""
            SELECT p
            FROM Pizza p
        """)*/
    List<PizzaProjection> findAllProjectedBy();

    List<PizzaProjection> findAllByNameLikeIgnoreCase(String name);

    Page<PizzaProjection> findAllProjectedBy(Pageable pageable);
}

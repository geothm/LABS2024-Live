package ro.irian.labs2024.pizza.domain.vo;

public class PizzaVO {

    private final Long id;
    private final String name;
    private final Long ingredientsCount;

    public PizzaVO(Long id, String name, Long ingredientsCount) {
        this.id = id;
        this.name = name;
        this.ingredientsCount = ingredientsCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getIngredientsCount() {
        return ingredientsCount;
    }
}

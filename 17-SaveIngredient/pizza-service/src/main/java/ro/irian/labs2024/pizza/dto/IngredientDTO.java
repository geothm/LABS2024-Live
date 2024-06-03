package ro.irian.labs2024.pizza.dto;

import jakarta.validation.constraints.NotBlank;
import ro.irian.labs2024.pizza.domain.entity.CurrencyValue;

public class IngredientDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @ValidName
    private String name;

    private CurrencyValue price;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, String name, CurrencyValue price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyValue getPrice() {
        return price;
    }

    public void setPrice(CurrencyValue price) {
        this.price = price;
    }
}

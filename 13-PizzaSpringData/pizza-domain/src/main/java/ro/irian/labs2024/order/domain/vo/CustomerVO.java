package ro.irian.labs2024.order.domain.vo;

import ro.irian.labs2024.pizza.domain.entity.Currency;

public record CustomerVO(Long id, String name, Double totalAmount, Currency currency) {
}

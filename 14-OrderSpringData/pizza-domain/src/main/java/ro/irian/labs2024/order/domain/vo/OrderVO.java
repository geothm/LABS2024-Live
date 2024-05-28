package ro.irian.labs2024.order.domain.vo;

import ro.irian.labs2024.pizza.domain.entity.CurrencyValue;

import java.time.LocalDateTime;

public record OrderVO(Long id, String customerName, LocalDateTime date, Long pizzasCount) {
}

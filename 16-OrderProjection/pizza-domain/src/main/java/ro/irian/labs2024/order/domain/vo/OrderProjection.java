package ro.irian.labs2024.order.domain.vo;

import java.time.LocalDateTime;

public interface OrderProjection {

    Long getId();

    CustomerProjection getCustomer();

    LocalDateTime getDate();
}

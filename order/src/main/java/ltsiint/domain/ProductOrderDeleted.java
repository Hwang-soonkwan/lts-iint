package ltsiint.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import ltsiint.domain.*;
import ltsiint.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProductOrderDeleted extends AbstractEvent {

    private String orderNumber;

    public ProductOrderDeleted(ProductOrder aggregate) {
        super(aggregate);
    }

    public ProductOrderDeleted() {
        super();
    }
}
//>>> DDD / Domain Event

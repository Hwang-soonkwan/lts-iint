package ltsiint.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import ltsiint.domain.*;
import ltsiint.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProductOrderCreated extends AbstractEvent {

    private String orderNumber;
    private String customerPartNumber;
    private String productName;
    private Address deliveryLocation;
    private Money unitPrice;
    private Integer orderQuantity;
    private Money orderAmount;
    private Date orderDate;
    private Date deliveryDate;
    private Boolean isUrgent;
    private Boolean isConfirmed;
    private String memo;

    public ProductOrderCreated(ProductOrder aggregate) {
        super(aggregate);
    }

    public ProductOrderCreated() {
        super();
    }
}
//>>> DDD / Domain Event

package ltsiint.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateProductOrderCommand {

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
}

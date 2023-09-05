package ltsiint.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import ltsiint.OrderApplication;
import ltsiint.domain.ProductOrderCreated;
import ltsiint.domain.ProductOrderDeleted;

@Entity
@Table(name = "ProductOrder_table")
@Data
//<<< DDD / Aggregate Root
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNumber;

    private String customerPartNumber;

    private String productName;

    @Embedded
    private Address deliveryLocation;

    @Embedded
    private Money orderAmount;

    private Integer orderQuantity;

    @Embedded
    private Money orderAmount;

    private Date orderDate;

    private Date deliveryDate;

    private Boolean isUrgent;

    private Boolean isConfirmed;

    private String memo;

    private Boolean isDeleted;

    @ElementCollection
    private List<OrderDetail> orderDetails;

    @PostPersist
    public void onPostPersist() {
        ProductOrderCreated productOrderCreated = new ProductOrderCreated(this);
        productOrderCreated.publishAfterCommit();

        ProductOrderDeleted productOrderDeleted = new ProductOrderDeleted(this);
        productOrderDeleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static ProductOrderRepository repository() {
        ProductOrderRepository productOrderRepository = OrderApplication.applicationContext.getBean(
            ProductOrderRepository.class
        );
        return productOrderRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateProductOrder(
        UpdateProductOrderCommand updateProductOrderCommand
    ) {
        //implement business logic here:

        ProductOrderUpdated productOrderUpdated = new ProductOrderUpdated(this);
        productOrderUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void productComplate(ProductComplateCommand productComplateCommand) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

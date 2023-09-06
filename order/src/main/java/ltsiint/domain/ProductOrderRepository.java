package ltsiint.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ltsiint.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel="productOrders", path="productOrders")
public interface ProductOrderRepository extends PagingAndSortingRepository<ProductOrder, Long>{
    @Query(value = "select productOrder " +
        "from ProductOrder productOrder " +
        "where productName = :productName")
       List<ProductOrder> findByOrderSearch       
(String productName, Pageable pageable);
}
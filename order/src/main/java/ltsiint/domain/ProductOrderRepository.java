package ltsiint.domain;

import ltsiint.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "productOrders",
    path = "productOrders"
)
public interface ProductOrderRepository
    extends PagingAndSortingRepository<ProductOrder, Long> {}
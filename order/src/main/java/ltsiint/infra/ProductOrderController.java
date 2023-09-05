package ltsiint.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import ltsiint.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/productOrders")
@Transactional
public class ProductOrderController {

    @Autowired
    ProductOrderRepository productOrderRepository;

    @RequestMapping(
        value = "productOrders/{id}/uri",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ProductOrder updateProductOrder(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateProductOrderCommand updateProductOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /productOrder/updateProductOrder  called #####"
        );
        Optional<ProductOrder> optionalProductOrder = productOrderRepository.findById(
            id
        );

        optionalProductOrder.orElseThrow(() -> new Exception("No Entity Found")
        );
        ProductOrder productOrder = optionalProductOrder.get();
        productOrder.updateProductOrder(updateProductOrderCommand);

        productOrderRepository.save(productOrder);
        return productOrder;
    }

    @RequestMapping(
        value = "productOrders/{id}/productcomplate",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ProductOrder productComplate(
        @PathVariable(value = "id") Long id,
        @RequestBody ProductComplateCommand productComplateCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /productOrder/productComplate  called #####");
        Optional<ProductOrder> optionalProductOrder = productOrderRepository.findById(
            id
        );

        optionalProductOrder.orElseThrow(() -> new Exception("No Entity Found")
        );
        ProductOrder productOrder = optionalProductOrder.get();
        productOrder.productComplate(productComplateCommand);

        productOrderRepository.save(productOrder);
        return productOrder;
    }
}
//>>> Clean Arch / Inbound Adaptor

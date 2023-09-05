package ltsiint.infra;

import ltsiint.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ProductOrder>> {

    @Override
    public EntityModel<ProductOrder> process(EntityModel<ProductOrder> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/uri")
                .withRel("uri")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "/productcomplate"
                )
                .withRel("productcomplate")
        );

        return model;
    }
}

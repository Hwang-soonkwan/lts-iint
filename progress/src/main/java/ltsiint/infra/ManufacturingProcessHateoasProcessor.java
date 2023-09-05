package ltsiint.infra;

import ltsiint.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ManufacturingProcessHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ManufacturingProcess>> {

    @Override
    public EntityModel<ManufacturingProcess> process(
        EntityModel<ManufacturingProcess> model
    ) {
        return model;
    }
}

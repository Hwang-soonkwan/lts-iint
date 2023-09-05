package ltsiint.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import ltsiint.domain.*;
import ltsiint.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ManufacturingProcessCreated extends AbstractEvent {

    private String processName;

    public ManufacturingProcessCreated(ManufacturingProcess aggregate) {
        super(aggregate);
    }

    public ManufacturingProcessCreated() {
        super();
    }
}
//>>> DDD / Domain Event

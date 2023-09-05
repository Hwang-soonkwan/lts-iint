package ltsiint.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import ltsiint.ProgressApplication;
import ltsiint.domain.ManufacturingProcessCreated;

@Entity
@Table(name = "ManufacturingProcess_table")
@Data
//<<< DDD / Aggregate Root
public class ManufacturingProcess {

    @Id
    private String processName;

    @PostPersist
    public void onPostPersist() {
        ManufacturingProcessCreated manufacturingProcessCreated = new ManufacturingProcessCreated(
            this
        );
        manufacturingProcessCreated.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static ManufacturingProcessRepository repository() {
        ManufacturingProcessRepository manufacturingProcessRepository = ProgressApplication.applicationContext.getBean(
            ManufacturingProcessRepository.class
        );
        return manufacturingProcessRepository;
    }
}
//>>> DDD / Aggregate Root

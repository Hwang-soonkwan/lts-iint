package ltsiint.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteProductOrderCommand {

    private String orderNumber;
}

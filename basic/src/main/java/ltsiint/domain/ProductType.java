package ltsiint.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum ProductType {
    완제품,
    반제품,
    단품,
}

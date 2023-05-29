package study.fire_fighting.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ksw
 * @since 2023-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Ffe implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eid", type = IdType.AUTO)
    private Long eid;

    private String ename;

    private BigDecimal price;

    private String manufactor;


}

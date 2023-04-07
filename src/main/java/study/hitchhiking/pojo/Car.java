package study.hitchhiking.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吴建豪
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "carID", type = IdType.AUTO)
    private String carID;

    @TableField("userID")
    private Long userID;

    private Integer seatnumber;

    private String platenumber;

    private BigDecimal fuelconsumption;

    private String oiltype;


}

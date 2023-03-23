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
 * @since 2023-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 汽车ID
     */
    @TableId(value = "carID", type = IdType.AUTO)
    private String carID;

    /**
     * 用户ID
     */
    @TableField("userID")
    private Long userID;

    /**
     * 座位数
     */
    private Integer seatnumber;

    /**
     * 车牌号
     */
    private String platenumber;

    /**
     * 百公里油耗
     */
    private BigDecimal fuelconsumption;

    /**
     * 汽油型号
     */
    private String oiltype;


}

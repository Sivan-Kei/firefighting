package study.hitchhiking.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @author test
 * @since 2023-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "orderID", type = IdType.AUTO)
    private Long orderID;

    /**
     * 订单状态
     */
    private String orderstatus;

    /**
     * 订单价格
     */
    private BigDecimal orderprice;

    /**
     * 汽车ID
     */
    @TableField("carID")
    private String carID;

    /**
     * 乘客上车点
     */
    private String getonposition;

    /**
     * 乘客上车时间
     */
    private Date getontime;

    /**
     * 乘客下车点
     */
    private String getoffposition;

    /**
     * 起点
     */
    private String threshold;

    /**
     * 终点
     */
    private String destination;

    /**
     * 发车时间
     */
    private Date departtime;


}

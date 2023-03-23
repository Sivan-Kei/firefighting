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
 * @author test
 * @since 2023-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Manage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("userID")
    private Long userID;

    /**
     * 订单ID
     */
    @TableField("orderID")
    private Long orderID;

    /**
     * 创始人ID
     */
    @TableField("initiatorID")
    private Long initiatorID;

    /**
     * 当前价格
     */
    private BigDecimal currentprice;

    /**
     * 请求议价
     */
    private BigDecimal requestedprice;

    /**
     * 司乘身份
     */
    private String role;


}

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
 * @author 吴建豪
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "orderID", type = IdType.AUTO)
    private Long orderID;

    private String orderstatus;

    private BigDecimal orderprice;

    @TableField("carID")
    private String carID;

    private String getonposition;

    private Date getontime;

    private String getoffposition;

    private String threshold;

    private String destination;

    private Date departtime;
}

package study.hitchhiking.pojo;

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
 * @since 2023-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "commentID", type = IdType.AUTO)
    private Long commentID;

    @TableField("orderID")
    private Long orderID;

    @TableField("userID")
    private Long userID;

    private String role;

    private String content;

    private Date commenttime;


}

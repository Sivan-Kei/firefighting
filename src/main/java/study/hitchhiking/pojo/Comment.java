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
 * @since 2023-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "commentID", type = IdType.AUTO)
    private Long commentID;

    /**
     * 用户ID
     */
    @TableField("userID")
    private Long userID;

    /**
     * 司乘身份
     */
    private String role;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date commenttime;


}

package study.hitchhiking.VO;

import lombok.Getter;
import study.hitchhiking.config.CONFIG;
import study.hitchhiking.pojo.Comment;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Getter
public class commentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long commentID;

    private Long orderID;

    private Long userID;

    private String role;

    private String content;

    private String commenttime;

    public commentVO(Comment comment) {
        this.commentID = comment.getCommentID();
        this.orderID = comment.getOrderID();
        this.userID = comment.getUserID();
        this.role = comment.getRole();
        this.content = comment.getContent();
        if(null != comment.getCommenttime()){
            commenttime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(comment.getCommenttime());
        }else{
            commenttime = CONFIG.DEFAULT_DATE;
        }
    }
}

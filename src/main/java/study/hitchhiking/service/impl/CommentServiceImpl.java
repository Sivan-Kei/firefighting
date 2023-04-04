package study.hitchhiking.service.impl;

import study.hitchhiking.pojo.Comment;
import study.hitchhiking.mapper.CommentMapper;
import study.hitchhiking.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴建豪
 * @since 2023-04-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

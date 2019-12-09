package com.jicl.pojo;

import com.jicl.entity.Comment;
import lombok.Data;

import java.util.List;

/**
 * 评论扩展类
 *
 * @author : xianzilei
 * @date : 2019/12/9 15:50
 */
@Data
public class CommentExtend extends Comment {
    /**
     * 评论用户昵称
     */
    private String nickname;
    /**
     * 评论用户头像
     */
    private String headPortrait;
    /**
     * 评论的回复数
     */
    private long replyNums;
    /**
     * 回复列表
     */
    private List<ReplyExtend> replyExtendList;
}

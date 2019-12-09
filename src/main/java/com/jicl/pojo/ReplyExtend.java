package com.jicl.pojo;

import com.jicl.entity.Reply;
import lombok.Data;

/**
 * 回复扩展类
 *
 * @author : xianzilei
 * @date : 2019/12/9 17:33
 */
@Data
public class ReplyExtend extends Reply {
    /**
     * 回复用户昵称
     */
    private String nickname;
    /**
     * 回复用户头像
     */
    private String headPortrait;
    /**
     * 被回复用户昵称
     */
    private String repliedNickname;
    /**
     * 被回复用户头像
     */
    private String repliedHeadPortrait;
}

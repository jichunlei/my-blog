package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 留言控制层
 *
 * @author : xianzilei
 * @date : 2019/12/12 18:41
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 功能描述: 跳转留言页
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/12 18:42
     **/
    @RequestMapping("/messages")
    public String toMessagePage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                Model model) {
        model.addAttribute("page", messageService.page(pageNum, pageSize));
        return BlogConstant.MESSAGE_PAGE;

    }
}

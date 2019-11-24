package com.jicl.vo;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 20:24
 * @Description: 博客查询条件
 */
public class BlogQuery {
    //标题
    private String title;
    //类型id
    private Long typeId;
    //是否推荐
    private boolean recommend;

    public BlogQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}

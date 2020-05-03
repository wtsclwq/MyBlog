package cn.sxh.sxh_blog.dto;

import cn.sxh.sxh_blog.entity.ArticleInfo;

/**
 * @author sxh
 */
public class ArticleAddDto extends ArticleInfo {


    private Long articleContentId;  //ArticleContent表的主键
    private String content;         //文章内容


    private Long categoryId;        // 分类ID
    private String categoryName;    // 分类名称
    private Byte categoryNumber;    // 分类对应的数量

    private Long articleCategoryId; // ArticleCategory表主键

    public Long getArticleContentId() {
        return articleContentId;
    }

    public void setArticleContentId(Long articleContentId) {
        this.articleContentId = articleContentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Byte getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Byte categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public Long getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }
}

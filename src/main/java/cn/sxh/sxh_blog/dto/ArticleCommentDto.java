package cn.sxh.sxh_blog.dto;

import cn.sxh.sxh_blog.entity.Comment;

/**
 * @author sxh
 */
public class ArticleCommentDto extends Comment {

    private Long articleCommentId;  // tbl_article_comment主键
    private Long articleId;         // 文章ID

    public Long getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Long articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}

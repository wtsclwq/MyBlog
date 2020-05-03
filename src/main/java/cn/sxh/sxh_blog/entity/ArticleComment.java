/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class ArticleComment implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = 5830456386553912157L;

    /** 
     */ 
    private Long id;

    /** 
     * 文章ID
     */ 
    private Long articleId;

    /** 
     * 对应的留言ID
     */ 
    private Long commentId;

    /** 
     * 创建时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 是否有效，默认为1有效，置0无效  默认：1
     */ 
    private Boolean isEffective;

    /** 
     * 获取 tbl_article_comment.id
     * @return tbl_article_comment.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 tbl_article_comment.id
     * @param id tbl_article_comment.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 文章ID tbl_article_comment.article_id
     * @return 文章ID
     */
    public final Long getArticleId() {
        return articleId;
    }

    /** 
     * 设置 文章ID tbl_article_comment.article_id
     * @param articleId 文章ID
     */
    public final void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /** 
     * 获取 对应的留言ID tbl_article_comment.comment_id
     * @return 对应的留言ID
     */
    public final Long getCommentId() {
        return commentId;
    }

    /** 
     * 设置 对应的留言ID tbl_article_comment.comment_id
     * @param commentId 对应的留言ID
     */
    public final void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /** 
     * 获取 创建时间 tbl_article_comment.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 tbl_article_comment.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 是否有效，默认为1有效，置0无效 tbl_article_comment.is_effective
     * @return 是否有效，默认为1有效，置0无效
     */
    public final Boolean getIsEffective() {
        return isEffective;
    }

    /** 
     * 设置 是否有效，默认为1有效，置0无效 tbl_article_comment.is_effective
     * @param isEffective 是否有效，默认为1有效，置0无效
     */
    public final void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", articleId=").append(articleId);
        sb.append(", commentId=").append(commentId);
        sb.append(", createBy=").append(createBy);
        sb.append(", isEffective=").append(isEffective);
        sb.append("]");
        return sb.toString();
    }
}
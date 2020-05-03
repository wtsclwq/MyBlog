/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class ArticleContent implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -5328096591072055057L;

    /** 
     */ 
    private Long id;

    /** 
     */ 
    private String content;

    /** 
     * 对应文章ID
     */ 
    private Long articleId;

    /** 
     * 创建时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 更新时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifieldBy;

    /** 
     * 获取 tbl_article_content.id
     * @return tbl_article_content.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 tbl_article_content.id
     * @param id tbl_article_content.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 tbl_article_content.content
     * @return tbl_article_content.content
     */
    public final String getContent() {
        return content;
    }

    /** 
     * 设置 tbl_article_content.content
     * @param content tbl_article_content.content
     */
    public final void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /** 
     * 获取 对应文章ID tbl_article_content.article_id
     * @return 对应文章ID
     */
    public final Long getArticleId() {
        return articleId;
    }

    /** 
     * 设置 对应文章ID tbl_article_content.article_id
     * @param articleId 对应文章ID
     */
    public final void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /** 
     * 获取 创建时间 tbl_article_content.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 tbl_article_content.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 更新时间 tbl_article_content.modifield_by
     * @return 更新时间
     */
    public final Date getModifieldBy() {
        return modifieldBy;
    }

    /** 
     * 设置 更新时间 tbl_article_content.modifield_by
     * @param modifieldBy 更新时间
     */
    public final void setModifieldBy(Date modifieldBy) {
        this.modifieldBy = modifieldBy;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", articleId=").append(articleId);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifieldBy=").append(modifieldBy);
        sb.append("]");
        return sb.toString();
    }
}
/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class ArticleCategory implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -8453899827131536775L;

    /** 
     */ 
    private Long id;

    /** 
     * 分类id
     */
    private Long categoryId;

    /** 
     * 文章id
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
    private Date modifiedBy;

    /** 
     * 获取 tbl_article_category.id
     * @return tbl_article_category.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 tbl_article_category.id
     * @param id tbl_article_category.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 分类id tbl_article_category.category_id
     * @return 分类id
     */
    public final Long getCategoryId() {
        return categoryId;
    }

    /** 
     * 设置 分类id tbl_article_category.category_id
     * @param categoryId 分类id
     */
    public final void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /** 
     * 获取 文章id tbl_article_category.article_id
     * @return 文章id
     */
    public final Long getArticleId() {
        return articleId;
    }

    /** 
     * 设置 文章id tbl_article_category.article_id
     * @param articleId 文章id
     */
    public final void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /** 
     * 获取 创建时间 tbl_article_category.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 tbl_article_category.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 更新时间 tbl_article_category.modified_by
     * @return 更新时间
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 更新时间 tbl_article_category.modified_by
     * @param modifiedBy 更新时间
     */
    public final void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", articleId=").append(articleId);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
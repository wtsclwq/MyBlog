/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class ArticleInfo implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -6691110528788253517L;

    /** 
     * 主键
     */ 
    private Long id;

    /** 
     * 文章标题
     */ 
    private String title;

    /** 
     * 文章简介，默认100个汉字以内
     */ 
    private String summary;

    /** 
     * 题图url
     */ 
    private String pictureUrl;

    /** 
     * 文章是否置顶，0为否，1为是  默认：0
     */ 
    private Boolean isTop;

    /** 
     * 文章访问量  默认：0
     */ 
    private Integer traffic;

    /** 
     * 创建时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 修改日期  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedBy;

    /** 
     * 获取 主键 tbl_article_info.id
     * @return 主键
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 主键 tbl_article_info.id
     * @param id 主键
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 文章标题 tbl_article_info.title
     * @return 文章标题
     */
    public final String getTitle() {
        return title;
    }

    /** 
     * 设置 文章标题 tbl_article_info.title
     * @param title 文章标题
     */
    public final void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /** 
     * 获取 文章简介，默认100个汉字以内 tbl_article_info.summary
     * @return 文章简介，默认100个汉字以内
     */
    public final String getSummary() {
        return summary;
    }

    /** 
     * 设置 文章简介，默认100个汉字以内 tbl_article_info.summary
     * @param summary 文章简介，默认100个汉字以内
     */
    public final void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /** 
     * 获取 题图url tbl_article_info.picture_url
     * @return 题图url
     */
    public final String getPictureUrl() {
        return pictureUrl;
    }

    /** 
     * 设置 题图url tbl_article_info.picture_url
     * @param pictureUrl 题图url
     */
    public final void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    /** 
     * 获取 文章是否置顶，0为否，1为是 tbl_article_info.is_top
     * @return 文章是否置顶，0为否，1为是
     */
    public final Boolean getIsTop() {
        return isTop;
    }

    /** 
     * 设置 文章是否置顶，0为否，1为是 tbl_article_info.is_top
     * @param isTop 文章是否置顶，0为否，1为是
     */
    public final void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /** 
     * 获取 文章访问量 tbl_article_info.traffic
     * @return 文章访问量
     */
    public final Integer getTraffic() {
        return traffic;
    }

    /** 
     * 设置 文章访问量 tbl_article_info.traffic
     * @param traffic 文章访问量
     */
    public final void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    /** 
     * 获取 创建时间 tbl_article_info.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 tbl_article_info.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 修改日期 tbl_article_info.modified_by
     * @return 修改日期
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 修改日期 tbl_article_info.modified_by
     * @param modifiedBy 修改日期
     */
    public final void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", summary=").append(summary);
        sb.append(", pictureUrl=").append(pictureUrl);
        sb.append(", isTop=").append(isTop);
        sb.append(", traffic=").append(traffic);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
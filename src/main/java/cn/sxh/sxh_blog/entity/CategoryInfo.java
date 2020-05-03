/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class CategoryInfo implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = 1402900630173886725L;

    /** 
     */ 
    private Long id;

    /** 
     * 分类名称
     */ 
    private String name;

    /** 
     * 该分类下的文章数量  默认：0
     */ 
    private Byte number;

    /** 
     * 分类创建时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     * 分类修改时间  默认：CURRENT_TIMESTAMP
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedBy;

    /** 
     * 获取 tbl_category_info.id
     * @return tbl_category_info.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 tbl_category_info.id
     * @param id tbl_category_info.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 分类名称 tbl_category_info.name
     * @return 分类名称
     */
    public final String getName() {
        return name;
    }

    /** 
     * 设置 分类名称 tbl_category_info.name
     * @param name 分类名称
     */
    public final void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 该分类下的文章数量 tbl_category_info.number
     * @return 该分类下的文章数量
     */
    public final Byte getNumber() {
        return number;
    }

    /** 
     * 设置 该分类下的文章数量 tbl_category_info.number
     * @param number 该分类下的文章数量
     */
    public final void setNumber(Byte number) {
        this.number = number;
    }

    /** 
     * 获取 分类创建时间 tbl_category_info.create_by
     * @return 分类创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 分类创建时间 tbl_category_info.create_by
     * @param createBy 分类创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 分类修改时间 tbl_category_info.modified_by
     * @return 分类修改时间
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 分类修改时间 tbl_category_info.modified_by
     * @param modifiedBy 分类修改时间
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
        sb.append(", name=").append(name);
        sb.append(", number=").append(number);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
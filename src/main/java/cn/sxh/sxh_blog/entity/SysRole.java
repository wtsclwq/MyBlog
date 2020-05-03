/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * 后台用户角色表 sys_role
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class SysRole implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -2299696162624590232L;

    /** 
     */ 
    private Long id;

    /** 
     */ 
    private String name;

    /** 
     */ 
    private String description;

    /** 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBy;

    /** 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedBy;

    /** 
     * 获取 sys_role.id
     * @return sys_role.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 sys_role.id
     * @param id sys_role.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 sys_role.name
     * @return sys_role.name
     */
    public final String getName() {
        return name;
    }

    /** 
     * 设置 sys_role.name
     * @param name sys_role.name
     */
    public final void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 sys_role.description
     * @return sys_role.description
     */
    public final String getDescription() {
        return description;
    }

    /** 
     * 设置 sys_role.description
     * @param description sys_role.description
     */
    public final void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /** 
     * 获取 sys_role.create_by
     * @return sys_role.create_by
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 sys_role.create_by
     * @param createBy sys_role.create_by
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 sys_role.modified_by
     * @return sys_role.modified_by
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 sys_role.modified_by
     * @param modifiedBy sys_role.modified_by
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
        sb.append(", description=").append(description);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
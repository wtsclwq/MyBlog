/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * 权限表 sys_permission
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class SysPermission implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -8144032691759274999L;

    /** 
     */ 
    private Long id;

    /** 
     * 父级权限的id
     */ 
    private Long parentId;

    /** 
     * 权限名称
     */ 
    private String name;

    /** 
     * 权限的唯一标识
     */ 
    private String permission;

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
     * 获取 sys_permission.id
     * @return sys_permission.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 sys_permission.id
     * @param id sys_permission.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 父级权限的id sys_permission.parent_id
     * @return 父级权限的id
     */
    public final Long getParentId() {
        return parentId;
    }

    /** 
     * 设置 父级权限的id sys_permission.parent_id
     * @param parentId 父级权限的id
     */
    public final void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /** 
     * 获取 权限名称 sys_permission.name
     * @return 权限名称
     */
    public final String getName() {
        return name;
    }

    /** 
     * 设置 权限名称 sys_permission.name
     * @param name 权限名称
     */
    public final void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 权限的唯一标识 sys_permission.permission
     * @return 权限的唯一标识
     */
    public final String getPermission() {
        return permission;
    }

    /** 
     * 设置 权限的唯一标识 sys_permission.permission
     * @param permission 权限的唯一标识
     */
    public final void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /** 
     * 获取 创建时间 sys_permission.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 sys_permission.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 更新时间 sys_permission.modified_by
     * @return 更新时间
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 更新时间 sys_permission.modified_by
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", permission=").append(permission);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
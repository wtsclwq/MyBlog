/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class SysRolePermission implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -7960243315658198374L;

    /** 
     */ 
    private Long id;

    /** 
     * 角色id
     */ 
    private Long roleId;

    /** 
     * 权限id
     */ 
    private Long permissionId;

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
     * 获取 sys_role_permission.id
     * @return sys_role_permission.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 sys_role_permission.id
     * @param id sys_role_permission.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 角色id sys_role_permission.role_id
     * @return 角色id
     */
    public final Long getRoleId() {
        return roleId;
    }

    /** 
     * 设置 角色id sys_role_permission.role_id
     * @param roleId 角色id
     */
    public final void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /** 
     * 获取 权限id sys_role_permission.permission_id
     * @return 权限id
     */
    public final Long getPermissionId() {
        return permissionId;
    }

    /** 
     * 设置 权限id sys_role_permission.permission_id
     * @param permissionId 权限id
     */
    public final void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /** 
     * 获取 创建时间 sys_role_permission.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 sys_role_permission.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 更新时间 sys_role_permission.modified_by
     * @return 更新时间
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 更新时间 sys_role_permission.modified_by
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
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
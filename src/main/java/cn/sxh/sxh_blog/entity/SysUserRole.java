/* https://github.com/orange1438 */
package cn.sxh.sxh_blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author orange1438 code generator
 * date:2020/04/14 21:19
 */
public class SysUserRole implements Serializable {
    /** 
     * 串行版本ID
    */
    private static final long serialVersionUID = -5331089515751855988L;

    /** 
     */ 
    private Long id;

    /** 
     * 用户id
     */ 
    private Long userId;

    /** 
     * 角色id
     */ 
    private Long roleId;

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
     * 获取 sys_user_role.id
     * @return sys_user_role.id
     */
    public final Long getId() {
        return id;
    }

    /** 
     * 设置 sys_user_role.id
     * @param id sys_user_role.id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /** 
     * 获取 用户id sys_user_role.user_id
     * @return 用户id
     */
    public final Long getUserId() {
        return userId;
    }

    /** 
     * 设置 用户id sys_user_role.user_id
     * @param userId 用户id
     */
    public final void setUserId(Long userId) {
        this.userId = userId;
    }

    /** 
     * 获取 角色id sys_user_role.role_id
     * @return 角色id
     */
    public final Long getRoleId() {
        return roleId;
    }

    /** 
     * 设置 角色id sys_user_role.role_id
     * @param roleId 角色id
     */
    public final void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /** 
     * 获取 创建时间 sys_user_role.create_by
     * @return 创建时间
     */
    public final Date getCreateBy() {
        return createBy;
    }

    /** 
     * 设置 创建时间 sys_user_role.create_by
     * @param createBy 创建时间
     */
    public final void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /** 
     * 获取 更新时间 sys_user_role.modified_by
     * @return 更新时间
     */
    public final Date getModifiedBy() {
        return modifiedBy;
    }

    /** 
     * 设置 更新时间 sys_user_role.modified_by
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
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append("]");
        return sb.toString();
    }
}
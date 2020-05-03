package cn.sxh.sxh_blog.dto;

import cn.sxh.sxh_blog.entity.SysRole;

import java.util.List;

/**
 * @author sxh
 */
public class SysRoleAddDto extends SysRole {

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}

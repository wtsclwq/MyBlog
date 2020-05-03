package cn.sxh.sxh_blog.dto;

import cn.sxh.sxh_blog.entity.SysPermission;
import cn.sxh.sxh_blog.entity.SysRole;

import java.util.List;

/**
 * @author sxh
 */
public class SysRoleSelectDto extends SysRole {

    private List<SysPermission> Permissions;

    public List<SysPermission> getPermissions() {
        return Permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        Permissions = permissions;
    }
}

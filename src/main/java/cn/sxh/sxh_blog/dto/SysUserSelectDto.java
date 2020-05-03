package cn.sxh.sxh_blog.dto;

import cn.sxh.sxh_blog.entity.SysRole;
import cn.sxh.sxh_blog.entity.SysUser;

import java.util.List;

/**
 * @author sxh
 */
public class SysUserSelectDto extends SysUser {
    List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}

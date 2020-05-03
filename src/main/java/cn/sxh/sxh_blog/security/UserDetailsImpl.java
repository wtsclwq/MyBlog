package cn.sxh.sxh_blog.security;

import cn.sxh.sxh_blog.entity.SysPermission;
import cn.sxh.sxh_blog.entity.SysRole;
import cn.sxh.sxh_blog.entity.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author sxh
 */
public class UserDetailsImpl extends SysUser implements UserDetails {
    private List<SysRole> roleList;

    private List<SysPermission> permissionList;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        // 设置角色
        if (!CollectionUtils.isEmpty(roleList)) {
            for (SysRole r : roleList) {
                if (r.getName().startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(r.getName()));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
                }
            }
        }
        // 设置权限标识
        if (!CollectionUtils.isEmpty(permissionList)) {
            for (SysPermission p : permissionList) {
                if (StringUtils.isNotBlank(p.getPermission())) {
                    collection.add(new SimpleGrantedAuthority(p.getPermission()));
                }
            }
        }
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString()+"UserDetailsImpl{" +
                "roleList=" + roleList +
                ", permissionList=" + permissionList +
                '}';
    }
}

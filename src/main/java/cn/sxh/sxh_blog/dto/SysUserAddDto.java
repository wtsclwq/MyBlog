package cn.sxh.sxh_blog.dto;

import cn.sxh.sxh_blog.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author sxh
 */
public class SysUserAddDto extends SysUser {

    @ApiModelProperty(value = "角色id集合", name = "roleIds", example = "[1,2,3]")
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

}

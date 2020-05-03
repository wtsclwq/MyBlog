package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.dto.SysRoleAddDto;
import cn.sxh.sxh_blog.entity.SysRole;
import cn.sxh.sxh_blog.service.SysRoleService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sxh
 */

@RestController
@RequestMapping(value = "/admin/role")
public class RoleController {

    @Resource
    private SysRoleService sysRoleService;


    @PostMapping
    @ApiOperation(value = "添加角色")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public PublicResultJson add(@RequestBody SysRoleAddDto role_add) {
        PublicResultJson resultJson = sysRoleService.add(role_add);
        return resultJson;
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "单、批量删除")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public PublicResultJson delete(@PathVariable(value = "id") Long id) {
        PublicResultJson resultJson = sysRoleService.delete(id);
        return resultJson;
    }

    @PutMapping
    @ApiOperation(value = "修改角色信息")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public PublicResultJson update(@RequestBody SysRoleAddDto role_add) {
        PublicResultJson resultJson = sysRoleService.update(role_add);
        return resultJson;
    }

    @GetMapping
    @ApiOperation(value = "模糊查询")
    @PreAuthorize("hasAnyAuthority('sys:role:query','sys:role:update','sys:role:del')")
    public LayuiTableResult select(SysRole role, @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        LayuiTableResult result = sysRoleService.select(role,page,pageSize);
        return result;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id查找角色、包括角色的权限")
    @PreAuthorize("hasAnyAuthority('sys:role:query','sys:role:update','sys:role:del')")
    public PublicResultJson selectById(@PathVariable(value = "id") Long id) {
        PublicResultJson resultJson = sysRoleService.selectById(id);
        return resultJson;
    }


}

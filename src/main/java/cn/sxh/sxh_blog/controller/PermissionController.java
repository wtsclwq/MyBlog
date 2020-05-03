package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.service.SysPermissionService;
import cn.sxh.sxh_blog.util.PublicResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sxh
 */

@Api(tags = "权限管理")
@RestController
@RequestMapping(value = "admin/permission")
public class PermissionController {

    @Resource
    private SysPermissionService sysPermissionService;
    @GetMapping
    @ApiOperation("获取所有权限")
    @PreAuthorize("hasAuthority('sys:permission:query')")
    public PublicResultJson getAllPermission(){
        PublicResultJson resultJson = sysPermissionService.select();
        return resultJson;
    }
}

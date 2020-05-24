package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.dto.SysUserAddDto;
import cn.sxh.sxh_blog.dto.SysUserSelectDto;
import cn.sxh.sxh_blog.entity.SysUser;
import cn.sxh.sxh_blog.service.SysUserService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import cn.sxh.sxh_blog.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sxh
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    @ApiOperation("用户列表条件模糊查询")
    @PreAuthorize("hasAnyAuthority('sys:admin:query','sys:admin:update','sys:admin:del')")
    public LayuiTableResult getUserByExample(SysUser user, @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        LayuiTableResult resultJson = sysUserService.selectByExample(user,page,pageSize);
        return resultJson;
    }

    @PostMapping
    @ApiOperation("添加用户")
    @PreAuthorize("hasAuthority('sys:admin:add')")
    public PublicResultJson addUser(@RequestBody SysUserAddDto userAddDto) {
        PublicResultJson resultJson = sysUserService.add(userAddDto);
        return resultJson;
    }

    @PutMapping
    @ApiOperation("更新用户")
    @PreAuthorize("hasAuthority('sys:admin:update')")
    public PublicResultJson updateUser(@RequestBody SysUserAddDto userAddDto) {
        PublicResultJson resultJson = sysUserService.updateByExample(userAddDto);
        return resultJson;
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据id删除用户")
    @PreAuthorize("hasAuthority('sys:admin:del')")
    public PublicResultJson deleteUser(@PathVariable(value = "id") Long id) {
        PublicResultJson resultJson = sysUserService.delete(id);
        return resultJson;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("根据uid查找、包括所拥有的角色")
    @PreAuthorize("hasAnyAuthority('sys:admin:query','sys:admin:update','sys:admin:del')")
    public PublicResultJson getUserById(@PathVariable(value = "id") Long id) {
        PublicResultJson resultJson = sysUserService.selectById(id);
        return resultJson;
    }

    @GetMapping(value = "/current")
    @ApiOperation("获取当前登录的用户")
    public PublicResultJson getCurrentUser() {
        PublicResultJson resultJson = sysUserService.getCurrentUserInfo();
        return resultJson;
    }

    @PutMapping(value = "/current")
    @ApiOperation("更新个人信息")
    public PublicResultJson updateMe(@RequestBody SysUser user) {
        PublicResultJson resultJson = sysUserService.updateCurrentInfo(user);
        return resultJson;
    }

    @PutMapping(value = "/current/pwd")
    @ApiOperation("更新个人密码")
    public PublicResultJson updatePassword(@RequestBody SysUser user) {
        SysUserSelectDto userSelectDto = (SysUserSelectDto) sysUserService.getCurrentUserInfo().getData();
        user.setId(userSelectDto.getId());
        user.setUsername(userSelectDto.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("test"+user);
        PublicResultJson resultJson = sysUserService.updateCurrentInfo(user);
        return resultJson;
    }

}

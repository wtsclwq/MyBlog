package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.dao.*;
import cn.sxh.sxh_blog.entity.*;
import cn.sxh.sxh_blog.security.UserDetailsImpl;
import cn.sxh.sxh_blog.service.LoginService;
import cn.sxh.sxh_blog.util.PublicResultJson;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sxh
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserDAO sysUserDAO;
    @Resource
    private SysUserRoleDAO sysUserRoleDAO;
    @Resource
    private SysRoleDAO sysRoleDAO;
    @Resource
    private SysRolePermissionDAO sysRolePermissionDAO;
    @Resource
    private SysPermissionDAO sysPermissionDAO;

    @Override
    public PublicResultJson getUserByName(String userName) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        SysUser user = selectUserByName(userName);
        //复制用户基本信息
        BeanUtils.copyProperties(user,userDetails);
        Long userId = user.getId();

        //查询角色
        SysUserRoleCriteria example1 = new SysUserRoleCriteria();
        SysUserRoleCriteria.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        List<SysUserRole> userRoleList = sysUserRoleDAO.selectByExample(example1);
        //获取角色id集合
        List<Long> roleIdList = userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

        //获取角色集合
        SysRoleCriteria example2 = new SysRoleCriteria();
        SysRoleCriteria.Criteria criteria2 = example2.createCriteria();
        criteria2.andIdIn(roleIdList);
        List<SysRole> roleList = sysRoleDAO.selectByExample(example2);
        userDetails.setRoleList(roleList);

        //查询权限
        SysRolePermissionCriteria example3 = new SysRolePermissionCriteria();
        SysRolePermissionCriteria.Criteria criteria3 = example3.createCriteria();
        criteria3.andRoleIdIn(roleIdList);
        List<SysRolePermission> rolePermissionList = sysRolePermissionDAO.selectByExample(example3);
        //获取权限id集合
        List<Long> permissionIdList = rolePermissionList.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList());

        //获取权限集合
        SysPermissionCriteria example4 = new SysPermissionCriteria();
        SysPermissionCriteria.Criteria criteria4 = example4.createCriteria();
        criteria4.andIdIn(permissionIdList);
        List<SysPermission> permissionList = sysPermissionDAO.selectByExample(example4);

        userDetails.setPermissionList(permissionList);


        return new PublicResultJson(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),userDetails);
    }

    private SysUser selectUserByName(String userName){
        SysUser result = new SysUser();
        SysUserCriteria example = new SysUserCriteria();
        SysUserCriteria.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        result = sysUserDAO.selectByExample(example).get(0);
        return result;
    }
}

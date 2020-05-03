package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.dao.SysPermissionDAO;
import cn.sxh.sxh_blog.dao.SysRoleDAO;
import cn.sxh.sxh_blog.dao.SysRolePermissionDAO;
import cn.sxh.sxh_blog.dao.SysUserRoleDAO;
import cn.sxh.sxh_blog.dto.SysRoleAddDto;
import cn.sxh.sxh_blog.dto.SysRoleSelectDto;
import cn.sxh.sxh_blog.entity.*;
import cn.sxh.sxh_blog.service.SysRoleService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDAO sysRoleDAO;

    @Resource
    private SysUserRoleDAO sysUserRoleDAO;

    @Resource
    private SysRolePermissionDAO sysRolePermissionDAO;

    @Resource
    private SysPermissionDAO sysPermissionDAO;

    @Override
    public PublicResultJson add(SysRoleAddDto role) {
        if (null != this.checkRoleName(role.getName())) {
            throw new IllegalArgumentException(role.getName() + "已存在");
        }
        SysRole roleResult = new SysRole();
        //复制属性
        BeanUtils.copyProperties(role, roleResult);
        sysRoleDAO.insertSelective(roleResult);
        // 保存角色权限
        Long newRoleId = getRoleLastestId();
        List<Long> permissionIds = role.getPermissionIds();
        this.saveRolePermissions(newRoleId, permissionIds);
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), role);
    }

    @Override
    public PublicResultJson delete(Long id) {
        //删除角色权限表中的纪录
        SysRolePermissionCriteria example1 = new SysRolePermissionCriteria();
        SysRolePermissionCriteria.Criteria criteria1 = example1.createCriteria();
        criteria1.andRoleIdEqualTo(id);
        sysRolePermissionDAO.deleteByExample(example1);

        //删除用户角色表中的纪录
        SysUserRoleCriteria example2 = new SysUserRoleCriteria();
        SysUserRoleCriteria.Criteria criteria2 = example2.createCriteria();
        criteria2.andRoleIdEqualTo(id);
        sysUserRoleDAO.deleteByExample(example2);

        //删除角色表中的纪录
        sysRoleDAO.deleteByPrimaryKey(id);
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public PublicResultJson update(SysRoleAddDto role) {
        SysRole check = this.checkRoleName(role.getName());
        //根据角色名检查出的角色不为空且角色id与检查出的角色id不相同
        //则证明角色表中存在与即将修改为的用户名相同的角色
        if (null != check && !check.getId().equals(role.getId())) {
            throw new IllegalArgumentException(role.getName() + "已存在");
        }

        SysRole roleResult = new SysRole();
        //复制属性
        BeanUtils.copyProperties(role, roleResult);
        //更新角色信息表
        sysRoleDAO.updateByPrimaryKeySelective(role);
        List<Long> permissionIds = role.getPermissionIds();
        // 修改权限
        this.saveRolePermissions(role.getId(), permissionIds);
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), role);
    }

    @Override
    public LayuiTableResult select(SysRole role, Integer page, Integer pageSize) {
        LayuiTableResult tableResult = new LayuiTableResult();
        //构造条件
        SysRoleCriteria example = new SysRoleCriteria();
        example.setOrderByClause("create_by DESC");
        SysRoleCriteria.Criteria criteria = example.createCriteria();

        //根据id精确查找
        if (null != role.getId()) {
            criteria.andIdEqualTo(role.getId());
        } else {
            if (StringUtils.isNotBlank(role.getName())) {
                criteria.andNameLike("%" + role.getName() + "%");
            }
        }

        //开启分页查找
        PageHelper.startPage(page, pageSize);
        List<SysRole> list = sysRoleDAO.selectByExample(example);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list, pageSize);
        tableResult.setCode(HttpStatus.OK.value());
        tableResult.setMessage(HttpStatus.OK.getReasonPhrase());
        tableResult.setData(pageInfo.getList());
        tableResult.setCount(pageInfo.getTotal());
        return tableResult;
    }

    @Override
    public PublicResultJson selectById(Long id) {
        if (null == id) {
            throw new IllegalArgumentException("id错误");
        }
        SysRole result = sysRoleDAO.selectByPrimaryKey(id);
        SysRoleSelectDto roleSelectDto = new SysRoleSelectDto();
        BeanUtils.copyProperties(result, roleSelectDto);

        SysRolePermissionCriteria example1 = new SysRolePermissionCriteria();
        SysRolePermissionCriteria.Criteria criteria1 = example1.createCriteria();
        criteria1.andRoleIdEqualTo(id);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionDAO.selectByExample(example1);

        //利用stream提取权限id集合
        List<Long> permissionIds = sysRolePermissions.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(permissionIds)) {
            SysPermissionCriteria example2 = new SysPermissionCriteria();
            SysPermissionCriteria.Criteria criteria2 = example2.createCriteria();
            criteria2.andIdIn(permissionIds);

            //设置权限集合
            List<SysPermission> permissions = sysPermissionDAO.selectByExample(example2);

            roleSelectDto.setPermissions(permissions);
        }
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), roleSelectDto);
    }

    private SysRole checkRoleName(String name) {
        SysRoleCriteria example = new SysRoleCriteria();
        SysRoleCriteria.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<SysRole> list = sysRoleDAO.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


    /**
     * @Description: 保存角色的权限，用于添加和修改角色
     * @Param: [roleId, permissionIds]
     * @return: void
     */
    private void saveRolePermissions(Long roleId, List<Long> permissionIds) {
        // 先删除该角色原有的角色权限表的纪录，再添加
        if (CollectionUtils.isNotEmpty(permissionIds)) {
            SysRolePermissionCriteria example = new SysRolePermissionCriteria();
            SysRolePermissionCriteria.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            sysRolePermissionDAO.deleteByExample(example);
        }
        SysRolePermission rolePermission = new SysRolePermission();
        rolePermission.setRoleId(roleId);
        // 遍历角色的权限id，插入
        for (Long pId : permissionIds) {
            rolePermission.setPermissionId(pId);
            sysRolePermissionDAO.insertSelective(rolePermission);
        }

    }


    /**
     * @Description: 获取最新添加的角色id
     * @Param: []
     * @return: java.lang.Long
     */
    private Long getRoleLastestId() {
        SysRoleCriteria example = new SysRoleCriteria();
        SysRoleCriteria.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        return sysRoleDAO.selectByExample(example).get(0).getId();
    }
}

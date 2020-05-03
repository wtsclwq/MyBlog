package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.dao.SysRoleDAO;
import cn.sxh.sxh_blog.dao.SysUserDAO;
import cn.sxh.sxh_blog.dao.SysUserRoleDAO;
import cn.sxh.sxh_blog.dto.SysUserAddDto;
import cn.sxh.sxh_blog.dto.SysUserSelectDto;
import cn.sxh.sxh_blog.entity.*;
import cn.sxh.sxh_blog.service.SysUserService;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sxh
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDAO sysUserDAO;

    @Resource
    private SysRoleDAO sysRoleDAO;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private SysUserRoleDAO sysUserRoleDAO;

    @Override
    public PublicResultJson add(SysUserAddDto user) {
        SysUser check = this.checkUsername(user.getUsername());
        if (null != check) {
            throw new IllegalArgumentException("用户名已存在");
        }
        SysUser resultUser = new SysUser();
        //使用BeanUtils复制属性
        BeanUtils.copyProperties(user, resultUser);

        if (StringUtils.isBlank(resultUser.getNickname())) {
            resultUser.setNickname(resultUser.getUsername());
        }
        resultUser.setPassword(passwordEncoder.encode(resultUser.getPassword()));
        resultUser.setStatus(1);
        sysUserDAO.insertSelective(resultUser);
        // 保存用户角色
        Long newUserId = getUserLastestId();
        saveUserRoles(newUserId, user.getRoleIds());
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    @Override
    public PublicResultJson delete(Long id) {
        //删除用户角色表中的纪录
        SysUserRoleCriteria example = new SysUserRoleCriteria();
        SysUserRoleCriteria.Criteria criteria1 = example.createCriteria();
        criteria1.andUserIdEqualTo(id);
        sysUserRoleDAO.deleteByExample(example);

        //删除用户表中的纪录
        sysUserDAO.deleteByPrimaryKey(id);
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public PublicResultJson updateByExample(SysUserAddDto user) {
        if (null == user.getId()) {
            throw new IllegalArgumentException("id错误");
        }
        SysUser check = this.checkUsername(user.getUsername());
        if (null != check && !(check.getId()).equals(user.getId())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        SysUser userResult = user;
        sysUserDAO.updateByPrimaryKeySelective(userResult);
        // 保存user权限
        saveUserRoles(userResult.getId(), user.getRoleIds());
        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    public LayuiTableResult selectByExample(SysUser user, Integer page, Integer pageSize) {
        LayuiTableResult resultJson = new LayuiTableResult();
        // 构造条件
        SysUserCriteria example = new SysUserCriteria();
        example.setOrderByClause("create_by DESC");
        SysUserCriteria.Criteria criteria = example.createCriteria();
        if (null != user.getId()) {
            // uid精确查找
            criteria.andIdEqualTo(user.getId());
        } else {
            if (StringUtils.isNotBlank(user.getUsername())) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (StringUtils.isNotBlank(user.getNickname())) {
                criteria.andNicknameLike("%" + user.getNickname() + "%");
            }
            if (null != user.getStatus()) {
                criteria.andStatusEqualTo(user.getStatus());
            }
        }
        // 开启分页查找
        PageHelper.startPage(page, pageSize);
        List<SysUser> list = sysUserDAO.selectByExample(example);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list, pageSize);
        //
        resultJson.setCode(HttpStatus.OK.value());
        resultJson.setMessage(HttpStatus.OK.getReasonPhrase());
        resultJson.setCount(pageInfo.getTotal());
        resultJson.setData(pageInfo.getList());
        return resultJson;
    }

    @Override
    public PublicResultJson selectById(Long id) {
        /*
         * 在阿里巴巴开发规范中，单操作两张表的时候应该建立索引； 这里是做链接查询，后期再看
         */
        if (null == id) {
            throw new IllegalArgumentException("uid错误");
        }
        SysUser result = sysUserDAO.selectByPrimaryKey(id);
        SysUserSelectDto userSelectDto = new SysUserSelectDto();
        BeanUtils.copyProperties(result, userSelectDto);

        SysUserRoleCriteria example1 = new SysUserRoleCriteria();
        SysUserRoleCriteria.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(id);
        List<SysUserRole> sysUserRoles = sysUserRoleDAO.selectByExample(example1);

        //利用stream提取角色id集合
        List<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

        SysRoleCriteria example2 = new SysRoleCriteria();
        SysRoleCriteria.Criteria criteria = example2.createCriteria();
        criteria.andIdIn(roleIds);

        //设置角色id
        List<SysRole> roles = sysRoleDAO.selectByExample(example2);

        userSelectDto.setRoles(roles);

        return new PublicResultJson(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userSelectDto);
    }

    @Override
    public PublicResultJson updateCurrentInfo(SysUser user) {
        return null;
    }


    /**
     * @Description: 检查该用户是否已存在
     * @Param: [username]
     * @return: cn.sxh.sxh_blog.entity.SysUser
     */
    private SysUser checkUsername(String username) {
        SysUserCriteria example = new SysUserCriteria();
        SysUserCriteria.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> list = sysUserDAO.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


    /**
     * @Description:保存用户角色
     * @Param: [uid, roleIds]
     * @return: void
     */
    private void saveUserRoles(Long uid, List<Long> roleIds) {
        // 先删除表中原有的用户-角色关系，再添加
        if (CollectionUtils.isNotEmpty(roleIds)) {
            SysUserRoleCriteria example = new SysUserRoleCriteria();
            SysUserRoleCriteria.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(uid);
            sysUserRoleDAO.deleteByExample(example);

            SysUserRole user_role = new SysUserRole();
            user_role.setUserId(uid);
            //遍历用户的角色id，插入
            for (Long r : roleIds) {
                user_role.setRoleId(r);
                sysUserRoleDAO.insertSelective(user_role);
            }
        }
    }

    /**
     * @Description: 获取最新插入的用户id
     * @Param: []
     * @return: java.lang.Long
     */
    private Long getUserLastestId() {
        SysUserCriteria example = new SysUserCriteria();
        example.setOrderByClause("id desc");
        return sysUserDAO.selectByExample(example).get(0).getId();
    }

}

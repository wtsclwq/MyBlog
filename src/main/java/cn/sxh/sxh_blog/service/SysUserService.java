package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.dto.SysUserAddDto;
import cn.sxh.sxh_blog.entity.SysUser;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;

/**
 * @author sxh
 */
public interface SysUserService {


    PublicResultJson add(SysUserAddDto user);

    PublicResultJson delete(Long id);

    PublicResultJson updateByExample(SysUserAddDto user);

    LayuiTableResult selectByExample(SysUser user, Integer page, Integer pageSize);

    PublicResultJson selectById(Long id);

    PublicResultJson updateCurrentInfo(SysUser user);

}

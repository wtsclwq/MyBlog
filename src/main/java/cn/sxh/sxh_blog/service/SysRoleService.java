package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.dto.SysRoleAddDto;
import cn.sxh.sxh_blog.entity.SysRole;
import cn.sxh.sxh_blog.util.LayuiTableResult;
import cn.sxh.sxh_blog.util.PublicResultJson;

/**
 * @author sxh
 */
public interface SysRoleService {

    PublicResultJson add(SysRoleAddDto role);

    PublicResultJson delete(Long id);

    PublicResultJson update(SysRoleAddDto role);

    LayuiTableResult select(SysRole role, Integer page, Integer pageSize);

    PublicResultJson selectById(Long id);

}

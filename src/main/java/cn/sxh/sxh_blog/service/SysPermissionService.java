package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.entity.SysPermission;
import cn.sxh.sxh_blog.util.PublicResultJson;

/**
 * @author sxh
 */
public interface SysPermissionService {


    PublicResultJson add(SysPermission permission);

    PublicResultJson delete(Long id);

    PublicResultJson update(SysPermission permission);

    PublicResultJson select();


}

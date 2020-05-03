package cn.sxh.sxh_blog.service.impl;

import cn.sxh.sxh_blog.dao.SysPermissionDAO;
import cn.sxh.sxh_blog.entity.SysPermission;
import cn.sxh.sxh_blog.entity.SysPermissionCriteria;
import cn.sxh.sxh_blog.service.SysPermissionService;
import cn.sxh.sxh_blog.util.PublicResultJson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sxh
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionDAO sysPermissionDAO;

    @Override
    public PublicResultJson add(SysPermission permission) {
        return null;
    }

    @Override
    public PublicResultJson delete(Long id) {
        return null;
    }

    @Override
    public PublicResultJson update(SysPermission permission) {
        return null;
    }

    @Override
    public PublicResultJson select() {
        SysPermissionCriteria example = new SysPermissionCriteria();
        List<SysPermission> sysPermissions = sysPermissionDAO.selectByExample(example);
        return new PublicResultJson(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),sysPermissions);
    }
}

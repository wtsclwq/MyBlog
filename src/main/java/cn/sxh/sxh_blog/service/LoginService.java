package cn.sxh.sxh_blog.service;

import cn.sxh.sxh_blog.util.PublicResultJson;

/**
 * @author sxh
 */
public interface LoginService {

    PublicResultJson getUserByName(String userName);

}

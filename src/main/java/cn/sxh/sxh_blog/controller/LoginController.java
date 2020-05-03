package cn.sxh.sxh_blog.controller;

import cn.sxh.sxh_blog.service.LoginService;
import cn.sxh.sxh_blog.util.PublicResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sxh
 */

@Api(tags = "  登录接口")
@RestController
public class LoginController {


    @Resource
    private LoginService loginService;
    @PostMapping
    @ApiOperation("登录校验")
    public PublicResultJson loginByUserName(@RequestParam(required = true) String userName,
                                            @RequestParam(required = true) String password) {


        if (loginService.getUserByName(userName) != null){

        }
        return new PublicResultJson();
    }

}

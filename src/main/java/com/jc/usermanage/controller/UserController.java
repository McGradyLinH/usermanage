package com.jc.usermanage.controller;

import com.jc.usermanage.domain.Account;
import com.jc.usermanage.service.TbUserService;
import com.jc.usermanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/12 14:26
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    @PostMapping("/login")
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户名", required = true),
            @ApiImplicitParam(name = "user_pwd", value = "密码", required = true)
    })
    public CommonResult<Account> login(String phone,String user_pwd) {
        Account account = tbUserService.login(phone, user_pwd);
        if (Objects.isNull(account))
            return new CommonResult<>(HttpServletResponse.SC_BAD_REQUEST, "用户名或密码错误！");
        else
            return new CommonResult<>(HttpServletResponse.SC_OK, "登录成功！", account);
    }
}

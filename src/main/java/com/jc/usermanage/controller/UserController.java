package com.jc.usermanage.controller;

import com.jc.usermanage.domain.*;
import com.jc.usermanage.service.TbUserRoleService;
import com.jc.usermanage.service.TbUserService;
import com.jc.usermanage.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/12 14:26
 */
@RestController
@Api(tags = "用户控制层")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbUserRoleService tbUserRoleService;

    @PostMapping("/login")
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true),
            @ApiImplicitParam(name = "user_pwd", value = "密码", required = true)
    })
    public CommonResult<Account> login(@RequestParam("phone") String phone, @RequestParam("user_pwd") String user_pwd) {
        Account account = tbUserService.login(phone, user_pwd);
        if (Objects.isNull(account)) {
            return new CommonResult<>(HttpServletResponse.SC_BAD_REQUEST, "用户名或密码错误！");
        } else {
            return new CommonResult<>(HttpServletResponse.SC_OK, "登录成功！", account);
        }
    }

    @ApiOperation("获取所有账户的信息")
    @PostMapping("/user")
    public CommonResult<List<Account>> getAllAccount() {
        List<Account> accounts = tbUserService.queryAllAccount(null);
        return new CommonResult<>(HttpServletResponse.SC_OK, "查询成功", accounts);
    }

    @ApiOperation("去到后端管理员登录界面")
    @GetMapping("/login")
    public ModelAndView toLogin() {
        return new ModelAndView("login");
    }

    @ApiOperation("后端管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true),
            @ApiImplicitParam(name = "user_pwd", value = "密码", required = true)
    })
    @PostMapping("/loginBack")
    public ModelAndView login1(@RequestParam("phone") String phone, @RequestParam("user_pwd") String user_pwd,
                               Model model, HttpSession session) {
        Account account = tbUserService.login(phone, user_pwd);
        if (Objects.isNull(account)) {
            model.addAttribute("msg", "用户名或密码错误");
            return new ModelAndView("login");
        }
        LocalDateTime now = LocalDateTime.now();
        if (account.getStatus().getStatus().contains("离职") || account.getStatus().getEnd_time().compareTo(now) < 0) {
            model.addAttribute("msg", "已离职或不是在职期，不支持登录");
            return new ModelAndView("login");
        }

        UserRole userRole = tbUserRoleService.queryById(account.getUser_id());
        if (userRole == null || !"admin".equals(userRole.getRole_name())) {
            model.addAttribute("msg", "请使用管理员账号登录");
            return new ModelAndView("login");
        }
        Integer companyId = account.getTbCompany().getCompanyId();
        session.setAttribute("companyid", companyId.toString());
        return new ModelAndView("redirect:/emps");
    }
}









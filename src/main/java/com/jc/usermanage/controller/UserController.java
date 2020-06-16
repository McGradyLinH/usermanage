package com.jc.usermanage.controller;

import com.jc.usermanage.domain.*;
import com.jc.usermanage.service.TbUserRoleService;
import com.jc.usermanage.service.TbUserService;
import com.jc.usermanage.util.CommonResult;
import com.jc.usermanage.util.ValidationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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

    @ApiOperation("测试用户新增的接口")
    @PostMapping("/insert")
    public CommonResult<User> insert(String name) {
        Account account = new Account();

        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setEmail("123456789@qq.com");
        userInfo.setAge(22);
        userInfo.setSex(true);
        userInfo.setBirth_time(LocalDateTime.of(1999, 3, 23, 8, 30, 0));
        userInfo.setBalance(new BigDecimal("1000"));
        userInfo.setPhone("13333333333");
        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
        userInfo.setUser_pwd(s);
        account.setInfo(userInfo);
        Dept dept = new Dept();
        dept.setDept_name("test");
        dept.setDept_id(1);
        dept.setDept_address("玉沙路");
        account.setDept(dept);
        try {
            //校验用户信息的合法性
            ValidationUtils.validate(userInfo);
            Status status = new Status();
            status.setStatus("在职");
            status.setStart_time(LocalDateTime.of(2019, 3, 23, 8, 30, 0));
            status.setEnd_time(LocalDateTime.of(2022, 3, 23, 8, 30, 0));
            //校验用户状态添加的合法性
            ValidationUtils.validate(status);
            account.setStatus(status);
            User user = tbUserService.insert(account);
            if (Objects.isNull(user)) {
                return new CommonResult<>(HttpServletResponse.SC_BAD_REQUEST, "新增用户发生错误！");
            }
            return new CommonResult<>(HttpServletResponse.SC_OK, "添加成功！", user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("参数校验失败！");
        }
        return new CommonResult<>(HttpServletResponse.SC_BAD_REQUEST, "新增用户发生错误！");
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









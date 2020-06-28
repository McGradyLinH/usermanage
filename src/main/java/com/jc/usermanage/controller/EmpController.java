package com.jc.usermanage.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.usermanage.domain.*;
import com.jc.usermanage.listener.UploadDataListener;
import com.jc.usermanage.service.TbCompanyService;
import com.jc.usermanage.service.TbDeptService;
import com.jc.usermanage.service.TbUserService;
import com.jc.usermanage.util.CommonResult;
import com.jc.usermanage.util.ValidationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/16 10:45
 */
@Api(tags = "员工管理控制成")
@Controller
public class EmpController {
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
    //一页的默认大小
    private static final Integer NUMS = 10;

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbDeptService tbDeptService;

    @Autowired
    private TbCompanyService tbCompanyService;

    @ApiOperation("根据登录管理员的公司获取相应公司的所有成员")
    @GetMapping("/emps")
    public String emps(HttpSession session, Model model, Integer pages) {
        String companyid = (String) session.getAttribute("companyid");
        if (StringUtils.isEmpty(companyid)) {
            model.addAttribute("msg", "请先登录");
            return "redirect:/login";
        }
        if (Objects.isNull(pages)) {
            pages = 1;
        }
        PageHelper.startPage(pages, NUMS);
        List<Account> accounts = tbUserService.queryAllAccount(companyid);
        PageInfo<Account> pageInfo = new PageInfo<>(accounts);

        model.addAttribute("emps", accounts);
        model.addAttribute("pages", pageInfo);
        return "emp/emps";
    }

    @ApiOperation("根据id获取员工的信息，并前往修改页面")
    @ApiImplicitParam(name = "id", paramType = "path", value = "员工id", required = true)
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, ModelMap map) {
        map.addAttribute("emp", tbUserService.queryByUserId(id));
        map.addAttribute("depts", tbDeptService.queryAll(null));
        return "emp/edit";
    }

    @ApiOperation(value = "修改员工的基本信息", httpMethod = "PUT")
    @ApiImplicitParam(name = "account", value = "页面传过来的员工信息", required = true)
    @PutMapping("/emp")
    public String editEmp(Account account, String start_time1, String end_time1) {

        System.err.println(account.getDept().getDept_id());
        //含有:说明没有变化
        if (!start_time1.contains(":")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(start_time1, dtf);
            LocalDate end = LocalDate.parse(end_time1, dtf);
            LocalTime now = LocalTime.now();
            account.getStatus().setStart_time(LocalDateTime.of(start, now));
            account.getStatus().setEnd_time(LocalDateTime.of(end, now));
        }
        tbUserService.update(account);
        return "redirect:/emps";
    }

    @ApiOperation(value = "进入添加员工页面")
    @GetMapping("/toAddPage")
    public String toAddPage(Model map) {
        map.addAttribute("depts", tbDeptService.queryAll(null));
        map.addAttribute("companys", tbCompanyService.queryAll(null));
        return "emp/add";
    }

    @ApiOperation("用户新增的接口")
    @PostMapping("/emp")
    public CommonResult<User> insert(Account account, String birthTime, String start_time1, String end_time1) {
        UserInfo userInfo = account.getInfo();
        Status status = account.getStatus();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //当前年月日时分秒
        LocalDateTime now = LocalDateTime.now();
        //当前时分秒
        LocalTime time = now.toLocalTime();
        userInfo.setBirth_time(LocalDateTime.of(LocalDate.parse(birthTime, formatter), time));
        //设置状态的起止时间
        status.setStart_time(LocalDateTime.of(LocalDate.parse(start_time1, formatter), time));
        status.setEnd_time(LocalDateTime.of(LocalDate.parse(end_time1, formatter), time));
        //计算年龄
        Duration duration = Duration.between(now, userInfo.getBirth_time());
        long days = duration.toDays();
        int year = (int) (days / 365);
        userInfo.setAge(year);
        try {
            //校验用户信息的合法性
            ValidationUtils.validate(userInfo);
            //校验用户状态添加的合法性
            ValidationUtils.validate(status);
            //设置默认密码
            String s = DigestUtils.md5DigestAsHex(userInfo.getUser_pwd().getBytes());
            userInfo.setUser_pwd(s);
            User user = tbUserService.insert(account);
            if (Objects.isNull(user)) {
                return new CommonResult<>(HttpServletResponse.SC_BAD_REQUEST, "新增用户发生错误！");
            }
            return new CommonResult<>(HttpServletResponse.SC_OK, "添加成功！", user);
        } catch (Exception e) {
            logger.info("参数检验失败{}", e.getMessage());
        }
        return new CommonResult<>(HttpServletResponse.SC_BAD_REQUEST, "新增用户发生错误！");
    }

    @ApiOperation("上传用户的Excel")
    @PostMapping("/emp/upload")
    public String uploadEmps(MultipartFile empExcel) {
        UploadDataListener listener = new UploadDataListener(this.tbUserService, this.tbCompanyService);
        try {
            //读取数据时存入到数据库中
            EasyExcel.read(empExcel.getInputStream(), UploadData.class, listener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/emps";
    }

}

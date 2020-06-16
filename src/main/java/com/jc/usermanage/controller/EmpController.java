package com.jc.usermanage.controller;

import com.jc.usermanage.domain.Account;
import com.jc.usermanage.service.TbDeptService;
import com.jc.usermanage.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/16 10:45
 */
@Api(tags = "员工管理控制成")
@Controller
public class EmpController {
    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbDeptService tbDeptService;

    @ApiOperation("根据登录管理员的公司获取相应公司的所有成员")
    @GetMapping("/emps")
    public String emps(HttpSession session, Model model) {
        String companyid = (String) session.getAttribute("companyid");
        if (StringUtils.isEmpty(companyid)) {
            model.addAttribute("msg", "请先登录");
            return "redirect:/login";
        }
        List<Account> accounts = tbUserService.queryAllAccount(companyid);
        model.addAttribute("emps", accounts);
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

    @ApiOperation(value = "修改员工的基本信息", httpMethod = "put")
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

}

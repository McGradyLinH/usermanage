package com.jc.usermanage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.jc.usermanage.domain.*;
import com.jc.usermanage.service.TbDeptService;
import com.jc.usermanage.service.TbStatusService;
import com.jc.usermanage.service.TbUserInfoService;
import com.jc.usermanage.service.TbUserService;
import com.jc.usermanage.util.ValidationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@SpringBootTest
class UsermanageApplicationTests {

    @Autowired
    private TbDeptService deptService;

    @Autowired
    private TbUserInfoService userInfoService;

    @Autowired
    private TbStatusService statusService;

    @Autowired
    private TbUserService userService;

    @Test
    void deptInsert() {
        Dept dept = new Dept();
        dept.setDept_name("test");
        dept.setDept_address("玉沙路");
        try {
            ValidationUtils.validate(dept);
            Dept dept1 = deptService.insert(dept);
            System.out.println(dept1);
        } catch (RuntimeException e) {
            System.out.println("参数校验失败！");
        }
    }

    @Test
//    @Transactional
    void insertUser() {
        Account account = new Account();

        UserInfo userInfo = new UserInfo();
        userInfo.setUser_info_id(0);
        userInfo.setName("test");
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
            ValidationUtils.validate(userInfo);
            Status status = new Status();
            status.setStatus("在职");
            status.setStart_time(LocalDateTime.of(2019, 3, 23, 8, 30, 0));
            status.setEnd_time(LocalDateTime.of(2022, 3, 23, 8, 30, 0));
            ValidationUtils.validate(status);
            account.setStatus(status);
            userService.insert(account);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("参数校验失败！");
        }
    }

    @Test
    void login() {
        Account account = userService.login("18888888888", "123456");
        System.out.println(account);
    }

    @Test
    void getAccount(){
        List<Account> accounts = userService.queryAllAccount();
        accounts.forEach(System.out::println);
    }

}

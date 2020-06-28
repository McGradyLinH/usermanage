package com.jc.usermanage.service.impl;

import com.jc.usermanage.dao.*;
import com.jc.usermanage.domain.*;
import com.jc.usermanage.service.TbUserService;
import com.jc.usermanage.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2020-06-11 13:04:25
 */
@Service("tbUserService")
public class TbUserServiceImpl implements TbUserService {
    private static final Logger logger = LoggerFactory.getLogger(TbUserServiceImpl.class);
    ;

    @Resource
    private TbUserDao tbUserDao;

    @Resource
    private TbUserInfoDao tbUserInfoDao;

    @Resource
    private TbStatusDao tbStatusDao;

    @Resource
    private TbDeptDao tbDeptDao;

    @Resource
    private TbCompanyDao tbCompanyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userId) {
        return this.tbUserDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.tbUserDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Account> queryAllAccount(String companyId) {
        return tbUserDao.queryAllAccount(companyId);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User insert(Account account) {
        User user = new User();
        user.setUser_dept_id(account.getDept().getDept_id());
        user.setUser_company_id(account.getTbCompany().getCompanyId());
        UserInfo info = account.getInfo();
        tbUserInfoDao.insert(info);
        user.setUser_info_id(info.getUser_info_id());
        Status status = account.getStatus();
        tbStatusDao.insert(status);
        user.setUser_status_id(status.getStatus_id());
        int insert = this.tbUserDao.insert(user);
        if (insert == 0) {
            logger.info("新增失败！");
            return null;
        }
        return user;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Account account) {
        User user = new User();
        user.setUser_dept_id(account.getDept().getDept_id());
        user.setUser_company_id(account.getTbCompany().getCompanyId());
        user.setUser_id(account.getUser_id());
        this.tbUserDao.update(user);
        //修改emp基本信息
        tbUserInfoDao.update(account.getInfo());
        //修改emp状态信息
        tbStatusDao.update(account.getStatus());

    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.tbUserDao.deleteById(userId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Account login(String phone, String pwd) {
        UserInfo userInfo = new UserInfo();
        Account account = new Account();
        userInfo.setPhone(phone);
        String s = DigestUtils.md5DigestAsHex(pwd.getBytes());
        userInfo.setUser_pwd(s);

        try {
            ValidationUtils.validate(userInfo);
            List<UserInfo> userInfos = tbUserInfoDao.queryAll(userInfo);
            if (!userInfos.isEmpty()) {
                UserInfo info = userInfos.get(0);
                User user = tbUserDao.queryById(info.getUser_info_id());
                account.setUser_id(user.getUser_id());
                account.setInfo(info);
                Dept dept = tbDeptDao.queryById(user.getUser_dept_id());
                account.setDept(dept);
                Status status = tbStatusDao.queryById(user.getUser_status_id());
                account.setStatus(status);
                TbCompany tbCompany = tbCompanyDao.queryById(user.getUser_company_id());
                account.setTbCompany(tbCompany);
                return account;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("查询异常-> {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Account queryByUserId(Integer userId) {
        return tbUserDao.queryByUserId(userId);
    }

    /**
     * excel 导入使用的添加方法
     * @param account 人员信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertapart(Account account) {
        User user = new User();
        if (null != account.getTbCompany()){
            user.setUser_company_id(account.getTbCompany().getCompanyId());
        }
        UserInfo info = account.getInfo();
        tbUserInfoDao.insert(info);
        user.setUser_info_id(info.getUser_info_id());
        Status status = account.getStatus();
        tbStatusDao.insert(status);
        user.setUser_status_id(status.getStatus_id());
        int insert = this.tbUserDao.insert(user);
        if (insert == 0) {
            logger.error("新增失败！");
            return 0;
        }
        return 1;
    }
}
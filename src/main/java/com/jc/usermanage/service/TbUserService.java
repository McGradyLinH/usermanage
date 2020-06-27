package com.jc.usermanage.service;

import com.jc.usermanage.domain.Account;
import com.jc.usermanage.domain.User;
import java.util.List;

/**
 * (TbUser)表服务接口
 *
 * @author makejava
 * @since 2020-06-11 13:04:25
 */
public interface TbUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 查询所有用户数据
     *
     * @return 对象列表
     */
    List<Account> queryAllAccount(String companyId);

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    User insert(Account account);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    void update(Account account);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

    /**
     * 登录
     * @param phone 电话做用户名
     * @param pwd 密码
     * @return 该用户的所有信息
     */
    Account login(String phone,String pwd);

    /**
     * 查询单条记录
     * @param userId 员工id
     * @return 员工所有信息
     */
    Account queryByUserId(Integer userId);

    /**
     * excel 导入使用的添加方法
     * @param account 人员信息
     * @return 是否成功
     */
    int insertapart(Account account);

}
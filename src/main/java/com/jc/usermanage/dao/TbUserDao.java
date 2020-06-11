package com.jc.usermanage.dao;

import com.jc.usermanage.domain.Account;
import com.jc.usermanage.domain.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-11 13:04:25
 */
public interface TbUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbUser 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User tbUser);

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int insert(User tbUser);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int update(User tbUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

    /**
     * 查询所有用户数据
     * @return 对象列表
     */
    List<Account> queryAllAccount();

}
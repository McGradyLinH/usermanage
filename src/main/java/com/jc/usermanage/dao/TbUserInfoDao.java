package com.jc.usermanage.dao;

import com.jc.usermanage.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbUserInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-11 10:45:13
 */
public interface TbUserInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userInfoId 主键
     * @return 实例对象
     */
    UserInfo queryById(Integer userInfoId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbUserInfo 实例对象
     * @return 对象列表
     */
    List<UserInfo> queryAll(UserInfo tbUserInfo);

    /**
     * 新增数据
     *
     * @param tbUserInfo 实例对象
     * @return 影响行数
     */
    int insert(UserInfo tbUserInfo);

    /**
     * 修改数据
     *
     * @param tbUserInfo 实例对象
     * @return 影响行数
     */
    int update(UserInfo tbUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param userInfoId 主键
     * @return 影响行数
     */
    int deleteById(Integer userInfoId);

}
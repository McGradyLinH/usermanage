package com.jc.usermanage.service;

import com.jc.usermanage.domain.UserInfo;
import java.util.List;

/**
 * (TbUserInfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-11 10:45:13
 */
public interface TbUserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param userInfoId 主键
     * @return 实例对象
     */
    UserInfo queryById(Integer userInfoId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(int offset, int limit);

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
     * @return 实例对象
     */
    UserInfo insert(UserInfo tbUserInfo);

    /**
     * 修改数据
     *
     * @param tbUserInfo 实例对象
     * @return 实例对象
     */
    UserInfo update(UserInfo tbUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param userInfoId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userInfoId);

}
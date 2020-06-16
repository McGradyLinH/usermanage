package com.jc.usermanage.service;

import com.jc.usermanage.domain.UserRole;

import java.util.List;

/**
 * (TbUserRole)表服务接口
 *
 * @author makejava
 * @since 2020-06-16 09:39:45
 */
public interface TbUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    UserRole queryById(Integer roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbUserRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole tbUserRole);

    /**
     * 修改数据
     *
     * @param tbUserRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole tbUserRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

}
package com.jc.usermanage.dao;

import com.jc.usermanage.domain.UserRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-16 09:39:45
 */
public interface TbUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    UserRole queryById(Integer roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbUserRole 实例对象
     * @return 对象列表
     */
    List<UserRole> queryAll(UserRole tbUserRole);

    /**
     * 新增数据
     *
     * @param tbUserRole 实例对象
     * @return 影响行数
     */
    int insert(UserRole tbUserRole);

    /**
     * 修改数据
     *
     * @param tbUserRole 实例对象
     * @return 影响行数
     */
    int update(UserRole tbUserRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

}
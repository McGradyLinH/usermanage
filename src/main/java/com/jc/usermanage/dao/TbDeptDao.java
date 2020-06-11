package com.jc.usermanage.dao;

import com.jc.usermanage.domain.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Dept)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-11 10:02:58
 */
public interface TbDeptDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    Dept queryById(Integer deptId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Dept> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param Dept 实例对象
     * @return 对象列表
     */
    List<Dept> queryAll(Dept Dept);

    /**
     * 新增数据
     *
     * @param Dept 实例对象
     * @return 影响行数
     */
    int insert(Dept Dept);

    /**
     * 修改数据
     *
     * @param Dept 实例对象
     * @return 影响行数
     */
    int update(Dept Dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 影响行数
     */
    int deleteById(Integer deptId);

}
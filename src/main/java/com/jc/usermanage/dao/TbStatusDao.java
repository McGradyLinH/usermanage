package com.jc.usermanage.dao;

import com.jc.usermanage.domain.Status;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbStatus)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-11 12:02:11
 */
public interface TbStatusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param statusId 主键
     * @return 实例对象
     */
    Status queryById(Integer statusId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Status> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbStatus 实例对象
     * @return 对象列表
     */
    List<Status> queryAll(Status tbStatus);

    /**
     * 新增数据
     *
     * @param tbStatus 实例对象
     * @return 影响行数
     */
    int insert(Status tbStatus);

    /**
     * 修改数据
     *
     * @param tbStatus 实例对象
     * @return 影响行数
     */
    int update(Status tbStatus);

    /**
     * 通过主键删除数据
     *
     * @param statusId 主键
     * @return 影响行数
     */
    int deleteById(Integer statusId);

}
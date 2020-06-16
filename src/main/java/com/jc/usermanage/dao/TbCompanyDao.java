package com.jc.usermanage.dao;

import com.jc.usermanage.domain.TbCompany;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbCompany)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-16 09:22:14
 */
public interface TbCompanyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    TbCompany queryById(Integer companyId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbCompany> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbCompany 实例对象
     * @return 对象列表
     */
    List<TbCompany> queryAll(TbCompany tbCompany);

    /**
     * 新增数据
     *
     * @param tbCompany 实例对象
     * @return 影响行数
     */
    int insert(TbCompany tbCompany);

    /**
     * 修改数据
     *
     * @param tbCompany 实例对象
     * @return 影响行数
     */
    int update(TbCompany tbCompany);

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 影响行数
     */
    int deleteById(Integer companyId);

}
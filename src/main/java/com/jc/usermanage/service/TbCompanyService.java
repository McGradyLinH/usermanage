package com.jc.usermanage.service;

import com.jc.usermanage.domain.TbCompany;
import java.util.List;

/**
 * (TbCompany)表服务接口
 *
 * @author makejava
 * @since 2020-06-16 09:22:14
 */
public interface TbCompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    TbCompany queryById(Integer companyId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbCompany> queryAllByLimit(int offset, int limit);

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
     * @return 实例对象
     */
    TbCompany insert(TbCompany tbCompany);

    /**
     * 修改数据
     *
     * @param tbCompany 实例对象
     * @return 实例对象
     */
    TbCompany update(TbCompany tbCompany);

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer companyId);

    /**
     * 通过公司名称查询单条数据
     *
     * @param companyName 公司名称
     * @return 实例对象
     */
    TbCompany queryByName(String companyName);

}
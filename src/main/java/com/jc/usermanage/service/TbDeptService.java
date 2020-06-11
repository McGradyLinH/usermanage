package com.jc.usermanage.service;

import com.jc.usermanage.domain.Dept;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * (TbDept)表服务接口
 *
 * @author makejava
 * @since 2020-06-11 10:02:59
 */
public interface TbDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    Dept queryById(Integer deptId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Dept> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbDept 实例对象
     * @return 实例对象
     */
    Dept insert(Dept tbDept);

    /**
     * 修改数据
     *
     * @param tbDept 实例对象
     * @return 实例对象
     */
    Dept update(Dept tbDept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer deptId);

}
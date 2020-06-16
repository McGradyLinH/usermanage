package com.jc.usermanage.service.impl;

import com.jc.usermanage.domain.Dept;
import com.jc.usermanage.dao.TbDeptDao;
import com.jc.usermanage.service.TbDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbDept)表服务实现类
 *
 * @author makejava
 * @since 2020-06-11 10:03:00
 */
@Service("tbDeptService")
public class TbDeptServiceImpl implements TbDeptService {
    @Resource
    private TbDeptDao tbDeptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public Dept queryById(Integer deptId) {
        return this.tbDeptDao.queryById(deptId);
    }

    @Override
    public List<Dept> queryAll(Dept dept) {
        return tbDeptDao.queryAll(dept);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Dept> queryAllByLimit(int offset, int limit) {
        return this.tbDeptDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbDept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept insert(Dept tbDept) {
        this.tbDeptDao.insert(tbDept);
        return tbDept;
    }

    /**
     * 修改数据
     *
     * @param tbDept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept update(Dept tbDept) {
        this.tbDeptDao.update(tbDept);
        return this.queryById(tbDept.getDept_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer deptId) {
        return this.tbDeptDao.deleteById(deptId) > 0;
    }
}
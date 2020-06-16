package com.jc.usermanage.service.impl;

import com.jc.usermanage.domain.TbCompany;
import com.jc.usermanage.dao.TbCompanyDao;
import com.jc.usermanage.service.TbCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbCompany)表服务实现类
 *
 * @author makejava
 * @since 2020-06-16 09:22:14
 */
@Service("tbCompanyService")
public class TbCompanyServiceImpl implements TbCompanyService {
    @Resource
    private TbCompanyDao tbCompanyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param companyId 主键
     * @return 实例对象
     */
    @Override
    public TbCompany queryById(Integer companyId) {
        return this.tbCompanyDao.queryById(companyId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbCompany> queryAllByLimit(int offset, int limit) {
        return this.tbCompanyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbCompany 实例对象
     * @return 实例对象
     */
    @Override
    public TbCompany insert(TbCompany tbCompany) {
        this.tbCompanyDao.insert(tbCompany);
        return tbCompany;
    }

    /**
     * 修改数据
     *
     * @param tbCompany 实例对象
     * @return 实例对象
     */
    @Override
    public TbCompany update(TbCompany tbCompany) {
        this.tbCompanyDao.update(tbCompany);
        return this.queryById(tbCompany.getCompanyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param companyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer companyId) {
        return this.tbCompanyDao.deleteById(companyId) > 0;
    }
}
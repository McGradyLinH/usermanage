package com.jc.usermanage.service.impl;

import com.jc.usermanage.domain.Status;
import com.jc.usermanage.dao.TbStatusDao;
import com.jc.usermanage.service.TbStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbStatus)表服务实现类
 *
 * @author makejava
 * @since 2020-06-11 12:02:11
 */
@Service("tbStatusService")
public class TbStatusServiceImpl implements TbStatusService {
    @Resource
    private TbStatusDao tbStatusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param statusId 主键
     * @return 实例对象
     */
    @Override
    public Status queryById(Integer statusId) {
        return this.tbStatusDao.queryById(statusId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Status> queryAllByLimit(int offset, int limit) {
        return this.tbStatusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbStatus 实例对象
     * @return 实例对象
     */
    @Override
    public Status insert(Status tbStatus) {
        this.tbStatusDao.insert(tbStatus);
        return tbStatus;
    }

    /**
     * 修改数据
     *
     * @param tbStatus 实例对象
     * @return 实例对象
     */
    @Override
    public Status update(Status tbStatus) {
        this.tbStatusDao.update(tbStatus);
        return this.queryById(tbStatus.getStatus_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param statusId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer statusId) {
        return this.tbStatusDao.deleteById(statusId) > 0;
    }
}
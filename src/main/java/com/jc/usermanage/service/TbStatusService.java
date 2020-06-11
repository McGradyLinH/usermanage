package com.jc.usermanage.service;

import com.jc.usermanage.domain.Status;
import java.util.List;

/**
 * (TbStatus)表服务接口
 *
 * @author makejava
 * @since 2020-06-11 12:02:11
 */
public interface TbStatusService {

    /**
     * 通过ID查询单条数据
     *
     * @param statusId 主键
     * @return 实例对象
     */
    Status queryById(Integer statusId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Status> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbStatus 实例对象
     * @return 实例对象
     */
    Status insert(Status tbStatus);

    /**
     * 修改数据
     *
     * @param tbStatus 实例对象
     * @return 实例对象
     */
    Status update(Status tbStatus);

    /**
     * 通过主键删除数据
     *
     * @param statusId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer statusId);

}
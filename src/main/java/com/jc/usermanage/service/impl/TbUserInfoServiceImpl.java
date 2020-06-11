package com.jc.usermanage.service.impl;

import com.jc.usermanage.domain.UserInfo;
import com.jc.usermanage.dao.TbUserInfoDao;
import com.jc.usermanage.service.TbUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbUserInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-11 10:45:13
 */
@Service("tbUserInfoService")
public class TbUserInfoServiceImpl implements TbUserInfoService {
    @Resource
    private TbUserInfoDao tbUserInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userInfoId 主键
     * @return 实例对象
     */
    @Override
    public UserInfo queryById(Integer userInfoId) {
        return this.tbUserInfoDao.queryById(userInfoId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserInfo> queryAllByLimit(int offset, int limit) {
        return this.tbUserInfoDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<UserInfo> queryAll(UserInfo tbUserInfo) {
        return this.tbUserInfoDao.queryAll(tbUserInfo);
    }

    /**
     * 新增数据
     *
     * @param tbUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserInfo insert(UserInfo tbUserInfo) {
        this.tbUserInfoDao.insert(tbUserInfo);
        return tbUserInfo;
    }

    /**
     * 修改数据
     *
     * @param tbUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserInfo update(UserInfo tbUserInfo) {
        this.tbUserInfoDao.update(tbUserInfo);
        return this.queryById(tbUserInfo.getUser_info_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param userInfoId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userInfoId) {
        return this.tbUserInfoDao.deleteById(userInfoId) > 0;
    }
}
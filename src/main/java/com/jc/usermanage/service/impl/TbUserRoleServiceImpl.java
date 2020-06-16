package com.jc.usermanage.service.impl;

import com.jc.usermanage.dao.TbUserRoleDao;
import com.jc.usermanage.domain.UserRole;
import com.jc.usermanage.service.TbUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-06-16 09:39:45
 */
@Service("tbUserRoleService")
public class TbUserRoleServiceImpl implements TbUserRoleService {
    @Resource
    private TbUserRoleDao tbUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public UserRole queryById(Integer roleId) {
        return this.tbUserRoleDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryAllByLimit(int offset, int limit) {
        return this.tbUserRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole tbUserRole) {
        this.tbUserRoleDao.insert(tbUserRole);
        return tbUserRole;
    }

    /**
     * 修改数据
     *
     * @param tbUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole update(UserRole tbUserRole) {
        this.tbUserRoleDao.update(tbUserRole);
        return this.queryById(tbUserRole.getUser_id());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.tbUserRoleDao.deleteById(roleId) > 0;
    }
}
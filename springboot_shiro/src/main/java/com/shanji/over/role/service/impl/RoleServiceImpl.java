package com.shanji.over.role.service.impl;

import com.shanji.over.perm.entity.Permission;
import com.shanji.over.role.entity.Role;
import com.shanji.over.role.mapper.RoleMapper;
import com.shanji.over.role.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2020-11-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService
{
    @Override
    public List<Permission> getPerms(Integer id)
    {
        return this.baseMapper.getPerms(id);
    }
}

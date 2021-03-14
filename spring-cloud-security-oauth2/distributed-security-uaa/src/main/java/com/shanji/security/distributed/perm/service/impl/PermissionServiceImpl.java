package com.shanji.security.distributed.perm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanji.security.distributed.perm.entity.Permission;
import com.shanji.security.distributed.perm.mapper.PermissionMapper;
import com.shanji.security.distributed.perm.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2021-01-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> getAll() {
        return this.baseMapper.selectAll();
    }
}

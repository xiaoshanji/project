package com.shanji.security.distributed.perm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanji.security.distributed.perm.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2021-01-31
 */
public interface PermissionService extends IService<Permission>
{
    List<Permission> getAll();
}

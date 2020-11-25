package com.shanji.over.role.service;

import com.shanji.over.perm.entity.Permission;
import com.shanji.over.role.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2020-11-09
 */
public interface RoleService extends IService<Role>
{
    List<Permission> getPerms(Integer id);
}

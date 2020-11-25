package com.shanji.over.role.mapper;

import com.shanji.over.perm.entity.Permission;
import com.shanji.over.role.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vicente
 * @since 2020-11-09
 */
@Repository
public interface RoleMapper extends BaseMapper<Role>
{
    List<Permission> getPerms(Integer id);
}

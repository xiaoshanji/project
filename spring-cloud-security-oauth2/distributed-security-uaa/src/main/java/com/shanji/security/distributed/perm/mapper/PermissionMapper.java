package com.shanji.security.distributed.perm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanji.security.distributed.perm.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vicente
 * @since 2021-01-31
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission>
{
    List<Permission> getPerms(Integer roleId);
    List<Permission> selectAll();
}

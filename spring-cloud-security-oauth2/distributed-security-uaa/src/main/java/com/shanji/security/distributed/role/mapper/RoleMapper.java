package com.shanji.security.distributed.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanji.security.distributed.role.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role>
{
    List<Role> getRoles(Integer userid);
}

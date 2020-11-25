package com.shanji.over.shoiroUser.mapper;

import com.shanji.over.role.entity.Role;
import com.shanji.over.shoiroUser.entity.ShiroUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vicente
 * @since 2020-11-01
 */
@Repository
public interface ShiroUserMapper extends BaseMapper<ShiroUser>
{
    ShiroUser getUserByName(String username);
    List<Role> getRolesById(Integer userid);
}

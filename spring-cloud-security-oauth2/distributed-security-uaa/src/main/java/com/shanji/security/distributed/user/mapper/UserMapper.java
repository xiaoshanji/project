package com.shanji.security.distributed.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanji.security.distributed.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vicente
 * @since 2020-12-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User>
{
    User selectByName(String userName);
}

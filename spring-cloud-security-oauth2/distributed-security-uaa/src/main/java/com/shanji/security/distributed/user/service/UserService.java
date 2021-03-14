package com.shanji.security.distributed.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanji.security.distributed.user.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2020-12-28
 */
public interface UserService extends IService<User>
{
    public User selectByName(String username);
    public int register(User user);
}

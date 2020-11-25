package com.shanji.over.shoiroUser.service;

import com.shanji.over.shoiroUser.entity.ShiroUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2020-11-01
 */
public interface ShiroUserService extends IService<ShiroUser>
{
    public boolean register(ShiroUser user);

    ShiroUser getUserByName(String username);

    ShiroUser getUserRoles(String username);
}

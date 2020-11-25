package com.shanji.over.shoiroUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanji.over.role.entity.Role;
import com.shanji.over.role.service.RoleService;
import com.shanji.over.shoiroUser.entity.ShiroUser;
import com.shanji.over.shoiroUser.mapper.ShiroUserMapper;
import com.shanji.over.shoiroUser.service.ShiroUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2020-11-01
 */
@Service
public class ShiroUserServiceImpl extends ServiceImpl<ShiroUserMapper, ShiroUser> implements ShiroUserService
{

    @Autowired
    private RoleService roleServiceImpl;

    @Override
    @Transactional
    public boolean register(ShiroUser user)
    {
        // 生成指定位数的随机字符串
        String salt = RandomStringUtils.randomAlphanumeric(10);

        Md5Hash passwd = new Md5Hash(user.getPassword(), salt,1024);

        user.setPassword(passwd.toHex());
        user.setSalt(salt);
        boolean save = this.save(user);
        return save;
    }

    @Override
    @Transactional
    public ShiroUser getUserByName(String username)
    {
//        // 生成查询条件
//        QueryWrapper<ShiroUser> wrapper = new QueryWrapper<>();
//        wrapper.eq("username",username);
//
//        // 查询，使用此方法如果查询到多个元祖，将报错，调用这些内置方法，如果实体属性与字段不能一一对应将报错
//        ShiroUser user = this.getOne(wrapper);


        ShiroUser user = this.baseMapper.getUserByName(username);

        return user;
    }

    @Transactional
    public ShiroUser getUserRoles(String username)
    {
        ShiroUser user = getUserByName(username);
        List<Role> roles = this.baseMapper.getRolesById(user.getId());
        roles.forEach(role -> {
            role.setPers(roleServiceImpl.getPerms(role.getId()));
        });
        user.setRoles(roles);
        return  user;
    }
}

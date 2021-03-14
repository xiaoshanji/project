package com.shanji.security.distributed.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanji.security.distributed.user.entity.User;
import com.shanji.security.distributed.user.mapper.UserMapper;
import com.shanji.security.distributed.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2020-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Transactional
    @Override
    public User selectByName(String username)
    {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("name",username);
//        return this.baseMapper.selectOne(wrapper);
        return this.baseMapper.selectByName(username);
    }

    @Override
    @Transactional
    public int register(User user)
    {
        String password = user.getPassword();
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(password);

        return this.baseMapper.insert(user);
    }
}

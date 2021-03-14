package com.shanji.security.distributed.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanji.security.distributed.role.entity.Role;
import com.shanji.security.distributed.role.mapper.RoleMapper;
import com.shanji.security.distributed.role.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2021-01-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

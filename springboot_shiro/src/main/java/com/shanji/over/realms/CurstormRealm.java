package com.shanji.over.realms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanji.over.salt.StyleSource;
import com.shanji.over.shoiroUser.entity.ShiroUser;
import com.shanji.over.shoiroUser.service.ShiroUserService;
import com.shanji.over.util.ApplicationUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version: V1.0
 * @className: CurstormRealm
 * @packageName: com.shanji.over.realms
 * @data: 2020/10/28 22:37
 * @description:
 */
public class CurstormRealm extends AuthorizingRealm
{

//    @Autowired
//    private ShiroUserService shiroUserServiceImpl;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {

        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();

        ShiroUserService shiroUserServiceImpl = (ShiroUserService) ApplicationUtil.getBeanByName("shiroUserServiceImpl");

        ShiroUser userRoles = shiroUserServiceImpl.getUserRoles(primaryPrincipal);


        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        userRoles.getRoles().forEach(role -> {
            authorizationInfo.addRole(role.getName());

            role.getPers().forEach(permission -> {
                authorizationInfo.addStringPermission(permission.getName());
            });

        });

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        System.out.println("开始认证----------------------------------->");
        // 获取用户名（凭证信息）
        String principal = (String) authenticationToken.getPrincipal();

        ShiroUserService shiroUserServiceImpl = (ShiroUserService) ApplicationUtil.getBeanByName("shiroUserServiceImpl");

        // 生成查询条件
        ShiroUser user = shiroUserServiceImpl.getUserByName(principal);

        if(user != null)
        {
            SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(principal,user.getPassword(), new StyleSource(user.getSalt()),this.getName());
            return simpleAuthorizationInfo;
        }
        return null;
    }
}

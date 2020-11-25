package com.shanji.over.config;

import com.shanji.over.realms.CurstormRealm;
import com.shanji.over.redis.StyleRedisManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @version: V1.0
 * @className: ShiroConfig
 * @packageName: com.shanji.over.config
 * @data: 2020/10/28 22:23
 * @description:
 */
@Configuration
public class ShiroConfig
{
    // 创建shiroFilter ：shiro拦截器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 配置公共资源啊与受限资源
        Map<String,String> map = new HashMap<>();
        map.put("/shiro-user/**","anon"); // 公共资源，这里 需要注意加入 map 的顺序
        map.put("/register.jsp","anon");
        map.put("/login.jsp","anon");
        map.put("/**","authc"); // 受限资源：需要认证  authc：过滤器


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 设置默认认证路径，如果不设置，默认为 /login.jsp
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }

    // 创建realm
    @Bean
    public Realm getRealm()
    {
        CurstormRealm curstormRealm = new CurstormRealm();

        //创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //s设置匹配算法为 md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数，如果注册时，对密码进行了散列处理，则在登录时，也要将输入的密码进行同样此处的散列处理
        credentialsMatcher.setHashIterations(1024);

        // 设置realm的凭证匹配器
        curstormRealm.setCredentialsMatcher(credentialsMatcher);


        // 开启缓存管理，使用 ehcache
        curstormRealm.setCacheManager(new StyleRedisManager());
        curstormRealm.setCachingEnabled(true); // 开启全局缓存
        curstormRealm.setAuthorizationCachingEnabled(true);  // 开启权限缓存
        curstormRealm.setAuthorizationCacheName("authorizationCache");
        curstormRealm.setAuthenticationCachingEnabled(true); // 开启认证缓存
        curstormRealm.setAuthenticationCacheName("authenticationCache");

        return curstormRealm;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm)
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }
}

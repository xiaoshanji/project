package com.shanji.security.distributed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @version: V1.0
 * @className: TokenConfig
 * @packageName: com.shanji.security.distributed.config
 * @data: 2021/3/2 17:50
 * @description:
 */
@Configuration
public class TokenConfig
{

    private String SIGNING_KEY = "xiaoshanshan";


    // 使用jwt存储令牌
    @Bean
    public TokenStore tokenStore()
    {
        return new JwtTokenStore(accessTokenConverter());
    }

     @Bean
    public JwtAccessTokenConverter accessTokenConverter()
     {
         JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
         converter.setSigningKey(SIGNING_KEY);   // 校验时需要使用相同的密钥
         return converter;
     }


    /*
    使用内存存储令牌
    @Bean
    public TokenStore tokenStore()
    {
        return new InMemoryTokenStore();
    }*/
}

package com.shanji.security.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @version: V1.0
 * @className: GatewayServer
 * @packageName: com.shanji.security.distributed
 * @data: 2021/3/10 17:37
 * @description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(GatewayServer.class,args);
    }
}

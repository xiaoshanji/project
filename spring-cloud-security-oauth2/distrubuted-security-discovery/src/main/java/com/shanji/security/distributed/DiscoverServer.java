package com.shanji.security.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @version: V1.0
 * @className: DiscoverServer
 * @packageName: com.shanji.security.distributed
 * @data: 2021/3/10 17:09
 * @description:
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoverServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(DiscoverServer.class,args);
    }
}

package com.shanji.security.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @version: V1.0
 * @className: OrderServer
 * @packageName: com.shanji.security.distributed
 * @data: 2021/3/2 16:39
 * @description:
 */

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderServer.class,args);
    }
}

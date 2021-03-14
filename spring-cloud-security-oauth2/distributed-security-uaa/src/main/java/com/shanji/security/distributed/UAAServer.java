package com.shanji.security.distributed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version: V1.0
 * @className: UAAServer
 * @packageName: com.shanji.security.distributed
 * @data: 2021/3/2 16:18
 * @description:
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"com.shanji.security.distributed"})
@MapperScan(value = "com.shanji.security.distributed*")
public class UAAServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(UAAServer.class,args);
    }
}

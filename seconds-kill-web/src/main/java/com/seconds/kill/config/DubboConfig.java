package com.seconds.kill.config;

import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * dubbo配置
 * author:jinjin
 * Date:2019/3/24 19:33
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.seconds.kill.controller")
public class DubboConfig {
}

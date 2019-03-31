package com.seconds.kill.annotation;

import java.lang.annotation.*;

/**
 * 自定义Redis限流注解
 * author:jinjin
 * Date:2019/3/27 23:23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisLimit {

    /**
     * 名字
     */
    String name() default "";

    /**
     * key
     */
    String key() default "";

    /**
     * 前缀
     */
    String prefix() default "limit";

    /**
     * 时间段-秒
     */
    int period();

    /**
     * 访问限制次数
     */
    int count();

    /**
     * 类型
     */
    LimitType limitType() default LimitType.CUSTOMER;

     enum LimitType {
        /**
         * 自定义key
         */
        CUSTOMER,
        /**
         * 根据请求者IP
         */
        IP;
    }
}

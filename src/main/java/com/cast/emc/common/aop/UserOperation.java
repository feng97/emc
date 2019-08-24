package com.cast.emc.common.aop;

import java.lang.annotation.*;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */

@Target({ElementType.METHOD, ElementType.TYPE}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface UserOperation {
    int type() default 0;
    String value() default "";
}

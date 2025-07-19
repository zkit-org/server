package org.zkit.support.server.message.api.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.transaction.Transactional;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Transactional
public @interface Activity {
    String action(); // 操作
    String userId(); // el 表达式，获取用户ID
    String url() default "";
    String title() default "";
}

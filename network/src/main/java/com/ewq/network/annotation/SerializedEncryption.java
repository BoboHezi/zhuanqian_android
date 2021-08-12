package com.ewq.network.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SerializedEncryption {
    /**
     * 加密类型：
     * RSA-Base64
     * RSA-BCD
     * 默认是 Base64
     */
    String type() default "Base64";
}

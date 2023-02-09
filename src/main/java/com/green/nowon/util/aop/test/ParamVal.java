package com.green.nowon.util.aop.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @formatter:off
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })@Retention(RetentionPolicy.RUNTIME)public @interface ParamVal {}
// @formatter:on
package com.green.nowon.util.aop.test;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

// @formatter:off
@Log4j2 @Aspect @Component
public class ParamValueAOP {
@Around("execution(* *..*Controller.*(..)) || paramV() || execution(* *com.green.nowon.*.*Service.*(..))")
public Object aop(final ProceedingJoinPoint joinPoint) throws Throwable {
final var sb=new StringBuilder();
Arrays.asList(joinPoint.getArgs()).forEach(t->{
if (t != null) {
sb.append("\n-------------------------------------\n")
.append("{}.{}의 파라미터     \n")
.append("데이터타입: {} ,  값: {}     ")
.append("\n-------------------------------------\n");
log.debug(sb.toString(),joinPoint.getTarget().getClass().getSimpleName(),((MethodSignature)joinPoint.getSignature()).getMethod().getName(),t.getClass().getSimpleName(),t);
sb.setLength(0);
}});
sb.setLength(0);
final var result = joinPoint.proceed();
sb.append("\n ============================================================ \n ")
.append("    {} 형: {} 반환         <<<<<<<<        ")
.append("\n ============================================================ \n ");
if (result != null) log.debug(sb.toString(), result.getClass().getSimpleName(),result);return result;}
@Pointcut("@annotation(com.green.nowon.util.aop.test.ParamVal)")private void paramV() {}}
// @formatter:on

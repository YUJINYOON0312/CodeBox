package com.green.nowon.util.aop.test;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j2;

// @formatter:off
/**
 * @link ParamVal
 * @author 0a23523375354
 */
@Log4j2 @Aspect @Component public class ParamValueAOP {@Around("execution(* *com.green.nowon.*.*Controller.*(..)) || paramV() || execution(* *com.green.nowon.*.*Service.*(..))")
public Object aop(final ProceedingJoinPoint joinPoint) throws Throwable {final var stopWatch = new StopWatch();stopWatch.start();
  final var sb = new StringBuilder("\n\n     --------------------------------------------------------------\n");
  Arrays.asList(joinPoint.getArgs()).forEach(arg -> {
    if (arg.getClass().getSimpleName().length() > 0 && arg != "" && arg != null) {sb.append("\n            메서드 : {}.{}").append("\n            타입: {}")
.append("\n            값: {}\n").append("\n     --------------------------------------------------------------\n");
log.debug(sb.toString(), joinPoint.getTarget().getClass().getSimpleName(),
((MethodSignature) joinPoint.getSignature()).getMethod().getName(), arg.getClass().getSimpleName(),getStrFormat(arg));}sb.setLength(0);
});final var result = joinPoint.proceed();stopWatch.stop();sb.setLength(0);final var msTot = stopWatch.getTotalTimeMillis() / 1000.0;
final var extMethod = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();final var extClass = joinPoint.getTarget().getClass().getSimpleName();
if (result != null) log.debug(sb.append("\n\n     ============================================================\n").append("\n            타입 : {}")
.append("\n            반환 : {}").append("\n            실행시간 : {}.{}({}ms)\n").append("\n     ============================================================\n").toString()
, result.getClass().getSimpleName(), result, extClass, extMethod, msTot);return result;}@Pointcut("@annotation(com.green.nowon.util.aop.test.ParamVal)")private void paramV() {}
private String getStrFormat(final Object arg) {final var a = arg.toString();if (a.contains(",")) return a.replace(",", ",    \n");return a;}}
// @formatter:on

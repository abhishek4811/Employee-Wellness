package com.wipro.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class EmployeeAspect {


	@Before("execution(* com.wipro.service.*.*(..))")
	public void logBeforeServiceMethods(JoinPoint joinPoint) {
		System.out.println("Executing method: " + joinPoint.getSignature().getName());
	}

	@AfterReturning("execution(* com.wipro.service.*.*(..))")
	public void logAfterServiceMethods(JoinPoint joinPoint) {
		System.out.println("Completed method: " + joinPoint.getSignature().getName());
	}


	@Pointcut("execution(* com.wipro.controller.EmployeeController.*(..))")
	public void applicationPackagePointcut() {
	}
	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering method : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				//explicitly invoke joinpoint method
				Object object = joinPoint.proceed();
				if (log.isDebugEnabled()) {					
					log.debug("Exiting method: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
							joinPoint.getSignature().getName(), object);
				}
				return object;
			} catch (Exception e) {
				log.error("Error in {}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
				log.error(e.getMessage());
				throw e;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}
}

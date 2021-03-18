package com.sampleproject.retailmart.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
	@Around("@annotation(com.sampleproject.retailmart.annotation.Traceable)")
	public Object logStartAndEnd(ProceedingJoinPoint pjp) throws Throwable
	{
		long startTime = System.currentTimeMillis();
		String className= pjp.getTarget().getClass().getCanonicalName();
		String methodName = pjp.getSignature().getName();
		log.info("started method: {}.{}",className,methodName);
		try
		{
			return pjp.proceed();
		}
		catch(Throwable e)
		{
			log.error("Exception in method : {}.{} -> {}",className,methodName,e);
			throw e;
		}
		finally {
			log.info("Method {}.{} exection time taken:{} seconds",className,methodName,((System.currentTimeMillis()-startTime)/1000f));
		}
	
	}

}

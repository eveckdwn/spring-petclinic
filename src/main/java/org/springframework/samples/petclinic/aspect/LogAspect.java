package org.springframework.samples.petclinic.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    
    /*
        @Around: 어떤 메소드가 호출되기 전이나 어떤 메소드가 호출된 뒤, 혹은 어떤 메소드에서 에러가 났을 때 모두 처리
        @annotation말고 @Bean으로도 가능하며, 표현식을 활용하여 @annotation을 안붙이고도 메소드에 적용할 수 있음.
    */
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return proceed;
    }
}
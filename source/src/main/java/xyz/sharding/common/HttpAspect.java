package xyz.sharding.common;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 定义http切面，对HTTP请求切入，进行日志记录
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void request() {
        // 切点，所有http请求的controller
    }

    @Around("request()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        logger.info("===========");
        logger.info("进入方法 {}.{}", className, methodName);
        logger.info("url= {}", request.getRequestURL());
        logger.info("method= {}", request.getMethod());
        logger.info("ip= {}", request.getRemoteAddr());
        logger.info("args={}", joinPoint.getArgs());
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("离开方法 {}.{} 耗时: {}ms", className, methodName, endTime - startTime);
        logger.info("===========");
        return proceed;
    }

}

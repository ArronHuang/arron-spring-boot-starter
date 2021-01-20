package com.arronhuang.boot.component;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
@Slf4j
public class ControllerAspect {

    @Autowired
    private ArronBootConfig arronBootConfig;

    @Around("execution(* com.arronhuang.*..controller.*Controller.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();

        if (arronBootConfig.isPrintRequestArchiveLog()) {
            long processTime = System.currentTimeMillis() - startTime;
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            Signature signature = pjp.getSignature();
            Object[] args = pjp.getArgs();
            Map<String, Object> paramMap = new HashMap<>();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
                String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
                for (int i = 0; i < parameterNames.length; i++) {
                    paramMap.put(parameterNames[i], args[i]);
                }
            }
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String uri = request.getRequestURI();
            log.info("\n【请求归档】\nURI: {}\nURL参数: {}\n请求体参数: {}\n处理时间: {}ms\n返回值: {}\n", uri, JSON.toJSONString(request.getParameterMap()), paramMap, processTime, JSON.toJSONString(result));
        }

        return result;
    }

}
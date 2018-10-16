package com.sailing.springbootmybatis.common.log.aop;

import com.alibaba.fastjson.JSON;
import com.sailing.springbootmybatis.bean.SysLog;
import com.sailing.springbootmybatis.common.log.annotation.MyLog;
import com.sailing.springbootmybatis.common.response.ResponseData;
import com.sailing.springbootmybatis.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common.log.aop
 * @Description: 日志切面处理类
 * @date 2018/10/15 16:11
 */
@Aspect//声明一个切面
@Component//必须添加此注解，可以让spring获取此切面
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.sailing.springbootmybatis.common.log.annotation.MyLog)")
    public void logPointCut(){

    }

    /**
     *   运行顺序：
     *   环绕通知开始
         前置通知
         环绕通知结束
         后置通知
         方法返回后调用
     */
//    @Before("logPointCut()")
    public void testBefore(){
        System.out.println("前置通知");
    }

//    @After("logPointCut()")
    public void testAfter(){
        System.out.println("后置通知");
    }

//    @Around("logPointCut()")
    public Object testAround(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知开始");
        Object result = null;
        try {
            result = joinPoint.proceed();//执行目标方法
            System.out.println(((ResponseData)result).getData());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕通知结束");
        return result;
    }

    @AfterReturning("logPointCut()")
    public void testAfterReturning(JoinPoint joinPoint){
        SysLog log = new SysLog();
        log.setUserName("admin");
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();

        MyLog myLog = method.getAnnotation(MyLog.class);
        if(myLog != null){
            log.setType(myLog.type().name());
            log.setOperation(myLog.value());
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();

        //获取请求的方法名
        String methodName = method.getName();
        log.setMethod(methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        log.setParams(params);

        //获取用户ip地址
        HttpServletRequest request = getRequest(joinPoint);
        String ip = getIpAddr(request);
        log.setIp(ip);
        log.setOperationTime(new Date());
        sysLogService.insertLog(log);
        System.out.println(log.getId());
    }

    /**
     * 获取参数request
     * @param point
     * @return
     */
    private HttpServletRequest getRequest(JoinPoint point) {
        Object[] args = point.getArgs();
        for (Object obj : args) {
            if (obj instanceof HttpServletRequest)
                return (HttpServletRequest) obj;
        }
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 获取访问者ip地址
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //@AfterThrowing("logPointCut()")
    public void testAfterThrowing(){
        System.out.println("方法抛出异常后调用");
    }
}

package com.example.demo.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.demo.domain.dto.OperationLogDTO;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.OperationLogService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.utils.result.ApiResult;
import com.example.utils.result.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;


// 参考写法 https://blog.csdn.net/lmiao1992/article/details/97891594

@Aspect
@Component
public class OperationLogAspect {


    @Autowired
    OperationLogService logDao;

    @Autowired
    UserService userService;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(com.example.demo.aop.OperationLogAnnotation)")
    public void operLogPoinCut() {
    }

    /**
     * 记录操作日志
     * @param joinPoint 方法的执行点
     * @param result  方法返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "result", value = "operLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, ApiResult result) throws Throwable {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            OperationLogDTO operationLog = new OperationLogDTO();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);
            if (annotation != null) {
                operationLog.setModel(annotation.operModul());
                operationLog.setType(annotation.operType());
                operationLog.setDescription(annotation.operDesc());
            }
            // 获取请求中的token信息
            String token = request.getHeader("token");
            String userName = "null";
            if(token != null){
                String userId = JWT.decode(token).getAudience().get(0);
                UserDTO user = userService.findUserById(userId);
                userName = user==null?"未知":user.getUsername();// 取到用户信息
            }else {
                UserDTO user = (UserDTO) joinPoint.getArgs()[0];
                userName = user.getUsername();
            }
            //参数,从切点出获取其参数
            Object[] params = joinPoint.getArgs();
            String param = "";

            for (int i = 0; i < params.length; i++) {
                param = param + params[i] + ",";
            }

            //操作时间
            operationLog.setOperationTime(Timestamp.valueOf(sdf.format(new Date())));
            //操作用户
            operationLog.setUserCode(userName);
            //操作IP
            operationLog.setIp(IPUtil.getIpAdrress(request));
            //返回值信息,200表示正常，不正常的是其他id
            operationLog.setResult(result.getCode().toString());
            // 请求参数
            operationLog.setParams(param!=""?param:"无参数");
            //保存日志
//            logDao.save(operationLog);  // 添加车辆的token，就没有添加

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
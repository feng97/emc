package com.cast.emc.common.aop;

import com.alibaba.fastjson.JSON;
import com.cast.emc.model.Operation;
import com.cast.emc.model.enums.OperationTypeEnum;
import com.cast.emc.service.OperationService;
import com.cast.emc.utils.HttpContextUtils;
import com.cast.emc.utils.IPUtils;
import com.cast.emc.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Map;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperationService operationService;
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.cast.emc.common.aop.UserOperation)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @Around("logPoinCut()")
    public void saveOperationLog(ProceedingJoinPoint joinPoint) throws Throwable{
        //保存日志
        Operation operation = new Operation();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        UserOperation userOperation = method.getAnnotation(UserOperation.class);
        if (userOperation != null) {
            String value = userOperation.value();
            int type = userOperation.type();
            operation.setOperationDataName(value);//保存获取的操作
            operation.setOperationType(OperationTypeEnum.findByCode(type).getContent());
        }
        // 环绕前获取参数

        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);

        // 增加操作需要区别新增与更新
        if (userOperation.type() == 1){
            Map map = JsonUtils.JsonToMap(params);
            if (map.get("id") != null) {
                operation.setOperationType(OperationTypeEnum.UPDATE.getContent());
            }
        }

        joinPoint.proceed();

        // 改变环绕后参数，获取全部属性
        args = joinPoint.getArgs();
        params = JSON.toJSONString(args[0]);
        System.out.println(params);
        operation.setOperationContent(params);
        operation.setOperationTime(Instant.now().toEpochMilli());

        //获取用户ip地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        operation.setOperationIp(IPUtils.getIpAddr(request));

        //调用service保存operation实体类到数据库
        operationService.saveOrUpdate(operation);
    }

}

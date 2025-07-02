package org.zkit.support.server.message.api.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.api.aspect.annotation.Activity;
import org.zkit.support.server.message.api.configuration.MessageConfiguration;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;
import org.zkit.support.server.message.api.holder.MetadataHolder;
import org.zkit.support.server.message.api.holder.NoticeUserHolder;
import org.zkit.support.server.message.api.service.ActivityService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ActivityAspect {
    
    @Resource
    private ActivityService activityService;
    @Resource
    private MessageConfiguration configuration;

    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    private final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Pointcut("@annotation(org.zkit.support.server.message.api.aspect.annotation.Activity)")
    public void activityPointCut() {
    }

    @Around("activityPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Activity activity = method.getAnnotation(Activity.class);
            String action = activity.action();
            String userId = activity.userId();
            String url = activity.url();
            String title = activity.title();
            String app = configuration.getApp();

            Long userIdValue = evaluateExpression(userId, joinPoint, Long.class);
            String urlValue = null;
            if(!url.isBlank()) {
                urlValue = evaluateExpression(url, joinPoint, String.class);
            }
            String titleValue = null;
            if(!title.isBlank()) {
                titleValue = evaluateExpression(title, joinPoint, String.class);
            }

            ActivityRequest request = new ActivityRequest()
                .setAction(action)
                .setApp(app)
                .setUserId(userIdValue)
                .setUrl(urlValue)
                .setTitle(titleValue)
                .setCreateTime(new Date())
                .setNoticeUserIds(NoticeUserHolder.get())
                .setMetadata(MetadataHolder.get());

            activityService.log(request);   

            return joinPoint.proceed();
        } finally {
            NoticeUserHolder.clear();
            MetadataHolder.clear();
        }
    }

    private <T> T evaluateExpression(String expression, ProceedingJoinPoint point, Class<T> desiredResultType) {
        // 获取目标对象
        Object target = point.getTarget();
        // 获取方法参数
        Object[] args = point.getArgs();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        EvaluationContext context = new MethodBasedEvaluationContext(target, method, args, parameterNameDiscoverer);
        Expression exp = spelExpressionParser.parseExpression(expression);
        return exp.getValue(context, desiredResultType);
    }

}

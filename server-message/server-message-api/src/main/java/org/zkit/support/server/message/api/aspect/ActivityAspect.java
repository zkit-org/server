package org.zkit.support.server.message.api.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.zkit.support.server.message.api.aspect.annotation.Activity;
import org.zkit.support.server.message.api.configuration.MessageConfiguration;
import org.zkit.support.server.message.api.entity.request.ActivityRequest;
import org.zkit.support.server.message.api.holder.MetadataHolder;
import org.zkit.support.server.message.api.holder.NoticeUserHolder;
import org.zkit.support.server.message.api.service.ActivityService;
import org.zkit.support.starter.boot.utils.SpelExpressionUtils;

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

    @Pointcut("@annotation(org.zkit.support.server.message.api.aspect.annotation.Activity)")
    public void activityPointCut() {
    }

    @After("activityPointCut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Activity activity = method.getAnnotation(Activity.class);
            String action = activity.action();
            String userId = activity.userId();
            String url = activity.url();
            String title = activity.title();
            String app = configuration.getApp();

            Long userIdValue = SpelExpressionUtils.evaluateExpression(userId, joinPoint, Long.class);
            String urlValue = null;
            if(!url.isBlank()) {
                urlValue = SpelExpressionUtils.evaluateExpression(url, joinPoint, String.class);
            }
            String titleValue = null;
            if(!title.isBlank()) {
                titleValue = SpelExpressionUtils.evaluateExpression(title, joinPoint, String.class);
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
        } finally {
            NoticeUserHolder.clear();
            MetadataHolder.clear();
        }
    }

}

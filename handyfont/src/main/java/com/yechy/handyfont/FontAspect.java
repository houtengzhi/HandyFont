package com.yechy.handyfont;

import android.graphics.Typeface;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by cloud on 2019-09-07.
 */
@Aspect
public class FontAspect {
    private static final String TAG = "FontAspect";

    private static final String POINTCUT_CRETAE_TYPEFACE =
            "execution(* android.graphics.Typeface.create(java.lang.String,int))";

    @Pointcut(POINTCUT_CRETAE_TYPEFACE)
    public void methodCreateTypeface() {
    }

    @Around("methodCreateTypeface()")
    public Object doCreateTypeface(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        Object arg0 = args[0];
        if (HandyFontConfig.getInstance().isReplaceFont() && arg0 != null) {
            String fontFamily = (String) arg0;
            Object obj = joinPoint.getThis();
            Typeface newTypeface = HandyUtil.createTypeface(AspecUtil.getContext(obj), fontFamily);
            if (newTypeface != null) {
                result = newTypeface;
            }
        }
        return result;
    }
}

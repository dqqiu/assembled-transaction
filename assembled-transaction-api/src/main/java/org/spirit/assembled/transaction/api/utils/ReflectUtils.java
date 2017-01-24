package org.spirit.assembled.transaction.api.utils;

import java.lang.reflect.Method;

import org.spirit.assembled.transaction.api.common.ReflectContext;
import org.spirit.assembled.transaction.api.exception.ReflectException;

/**
 * @description 反射工具类
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:29:06 
 */
public class ReflectUtils {
  private ReflectUtils() {}
  
  /**
   *  @description 方法调用
   *  @param reflectContext 反射调用上下文
   *  @return 调用结果
   *  @createTime 2017年1月10日 下午3:49:14 
   *  @author qiudequan
   */
  public static Object invoke(ReflectContext reflectContext) {
    if (StringUtils.isNotEmpty(reflectContext.getTargetMethod())) {
      try {
        Object target = BeanFactoryUtils.getBean(reflectContext.getTargetClass());
        if (target == null && !reflectContext.getTargetClass().isInterface()) {
          target = reflectContext.getTargetClass().newInstance();
        }
        Method method = target.getClass().getMethod(reflectContext.getTargetMethod(), reflectContext.getParams());
        return method.invoke(target, reflectContext.getArgs());
      } catch (Exception e) {
        throw new ReflectException(e);
      }
    }
    return null;
  }
}

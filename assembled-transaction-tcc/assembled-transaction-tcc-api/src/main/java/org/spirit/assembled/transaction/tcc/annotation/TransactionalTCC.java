package org.spirit.assembled.transaction.tcc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 事务注解
 * @author qiudequan
 * @createTime : 2017年1月6日 下午4:10:26 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface TransactionalTCC {
  /**
   *  @description 确认方法名称
   *  @createTime 2017年1月6日 下午4:11:40 
   *  @author qiudequan
   */
  String confirm() default "";
  /**
   *  @description 取消方法名称
   *  @createTime 2017年1月6日 下午4:11:47 
   *  @author qiudequan
   */
  String cancel() default "";
}

package org.spirit.assembled.transaction.tcc.support.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

/**
 * @description TCC事务注解切面
 * @author qiudequan
 * @createTime 2017年1月12日 下午3:08:25 
 */
@Aspect
public class TransactionalTCCAspect implements Ordered {

  private int order = Ordered.HIGHEST_PRECEDENCE;
  
  @Pointcut(value = "@annotation(org.spirit.assembled.transaction.tcc.annotation.TransactionalTCC)")
  public void tccPointcut(){
    // tcc切入点
  }
  
  @Around(value = "tccPointcut()")
  public void tccAround() {
    // tcc环绕增强
  }
  
  @Override
  public int getOrder() {
    return order;
  }
  
}

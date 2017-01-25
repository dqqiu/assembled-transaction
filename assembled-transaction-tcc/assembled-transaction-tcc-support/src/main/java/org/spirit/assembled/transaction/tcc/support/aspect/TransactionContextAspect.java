package org.spirit.assembled.transaction.tcc.support.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.spirit.assembled.transaction.tcc.support.role.Coordinator;
import org.springframework.core.Ordered;

/**
 * @description 事务上下文切面
 * @author qiudequan
 * @createTime 2017年1月25日 下午1:47:47 
 */
@Aspect
public class TransactionContextAspect implements Ordered {
  private int order = Ordered.HIGHEST_PRECEDENCE;
  
  private Coordinator coordinator;
  
  @Pointcut(value = "@annotation(org.spirit.assembled.transaction.tcc.annotation.TransactionalTCC)")
  public void transactionContextPointcut(){
    // TransactionContext切入点
  }
  
  @Around(value = "transactionContextPointcut()")
  public Object transactionContextAround(ProceedingJoinPoint joinPoint) throws Throwable {
    // TransactionContext环绕增强
    return coordinator.assistTransactionContext(joinPoint);
  }
  
  @Override
  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public void setCoordinator(Coordinator coordinator) {
    this.coordinator = coordinator;
  }
  
}

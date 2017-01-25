package org.spirit.assembled.transaction.tcc.support.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.assembled.transaction.api.utils.ObjectUtils;
import org.spirit.assembled.transaction.tcc.TransactionContext;
import org.spirit.assembled.transaction.tcc.TransactionStatus;
import org.spirit.assembled.transaction.tcc.configuration.TransactionConfiguration;
import org.spirit.assembled.transaction.tcc.exception.NoExistTransactionException;
import org.spirit.assembled.transaction.tcc.manager.TransactionManager;
import org.spirit.assembled.transaction.tcc.role.TransactionMethodRole;
import org.spirit.assembled.transaction.tcc.support.TransactionContextHelper;
import org.spirit.assembled.transaction.tcc.support.role.analyze.TransactionMethodRoleAnalyzer;

/**
 * @description 事务补偿注解拦截器
 * @author qiudequan
 * @createTime 2017年1月12日 下午3:38:42 
 */
public class TransactionalTCCInterceptor {
  static final Logger LOG = LoggerFactory.getLogger(TransactionalTCCInterceptor.class.getSimpleName());

  private TransactionConfiguration transactionConfiguration;

  public TransactionConfiguration getTransactionConfiguration() {
    return transactionConfiguration;
  }

  public void setTransactionConfiguration(TransactionConfiguration transactionConfiguration) {
    this.transactionConfiguration = transactionConfiguration;
  }

  /**
   *  @description 拦截事务补偿方法
   *  @param joinPoint	AOP切点
   *  @return void
   *  @createTime 2017年1月25日 上午10:15:43 
   *  @author qiudequan
   *  @throws Throwable 异常
   */
  public Object interceptorTccMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    TransactionContext transactionContext = TransactionContextHelper.getTransactionContextFromArgs(joinPoint.getArgs());
    TransactionMethodRole methodRole = TransactionMethodRoleAnalyzer.analyzeMethodRole(transactionContext, true);
    switch (methodRole) {
      case ORIGINATOR:
        return proceedOriginatorMethod(joinPoint); 
      case PROVIDER:
        return proceedProviderMethod(joinPoint, transactionContext);
      default:
        return joinPoint.proceed();
    }
  }

  /**
   *  @description 执行事务补偿发起方法,此时处于try阶段
   *  @param joinPoint 事务补偿AOP切入点
   *  @param transactionContext 事务补偿上下文
   *  @return 执行结果
   *  @createTime 2017年1月25日 上午10:22:07 
   *  @author qiudequan
   * @throws Throwable 
   */
  private Object proceedProviderMethod(ProceedingJoinPoint joinPoint,
      TransactionContext transactionContext) throws Throwable {
    TransactionManager transactionManager = transactionConfiguration.getTransactionManager();
    switch (TransactionStatus.valueOf(transactionContext.getStatus())) {
      case TRY:
        // try阶段,根据当前事务上下文信息新建一个事务,并执行方法
        transactionManager.newTransaction(transactionContext);
        return joinPoint.proceed();
      case CONFIRM:
      case CANCEL:
        transactionHandler(transactionManager, transactionContext);
        break;
      default:
        break;
    }
    Method method = ((MethodSignature) (joinPoint.getSignature())).getMethod();
    // 对于要执行方法，都给予一个默认的返回值
    return ObjectUtils.getDefaultValue(method.getReturnType());
  }

  /**
   *  @description 事务处理
   *  @param transactionManager 事务管理器  
   *  @param transactionContext 事务上下文
   *  @return void
   *  @createTime 2017年1月25日 上午10:23:19
   *  @author qiudequan
   */
  private void transactionHandler(TransactionManager transactionManager, TransactionContext transactionContext) {
    try {
      transactionManager.begin(transactionContext);
      if(TransactionStatus.valueOf(transactionContext.getStatus()) == TransactionStatus.CONFIRM) {
        transactionManager.commit();
      }
      if(TransactionStatus.valueOf(transactionContext.getStatus()) == TransactionStatus.CANCEL) {
        transactionManager.rollback();
      }
    } catch (NoExistTransactionException e) {
      LOG.warn("This transaction does not exist or has been committed!");
    }
  }

  private Object proceedOriginatorMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    // 开始事务
    transactionConfiguration.getTransactionManager().begin();
    Object result = null;
    try {
      result = joinPoint.proceed();
    } catch (Throwable e) {
      LOG.error("Process tcc transaction method failed in the try phase!", e);
      // 回滚事务
      transactionConfiguration.getTransactionManager().rollback();
      throw e;
    }

    // 提交事务
    transactionConfiguration.getTransactionManager().commit();
    return result;
  }


}

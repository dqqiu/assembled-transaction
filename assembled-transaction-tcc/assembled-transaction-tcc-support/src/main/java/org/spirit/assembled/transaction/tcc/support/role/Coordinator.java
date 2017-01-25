package org.spirit.assembled.transaction.tcc.support.role;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.spirit.assembled.transaction.api.common.ReflectContext;
import org.spirit.assembled.transaction.api.utils.ReflectUtils;
import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionContext;
import org.spirit.assembled.transaction.tcc.TransactionStatus;
import org.spirit.assembled.transaction.tcc.TransactionXid;
import org.spirit.assembled.transaction.tcc.annotation.TransactionalTCC;
import org.spirit.assembled.transaction.tcc.configuration.TransactionConfiguration;
import org.spirit.assembled.transaction.tcc.role.Executor;
import org.spirit.assembled.transaction.tcc.role.Participant;
import org.spirit.assembled.transaction.tcc.role.TransactionMethodRole;
import org.spirit.assembled.transaction.tcc.support.TransactionContextHelper;
import org.spirit.assembled.transaction.tcc.support.role.analyze.TransactionMethodRoleAnalyzer;

/**
 * @description 协调者，全局唯一，主要进行资源的协调工作
 * @author qiudequan
 * @createTime 2017年1月10日 下午1:20:26 
 */
public class Coordinator {
  protected TransactionConfiguration transactionConfiguration;

  public TransactionConfiguration getTransactionConfiguration() {
    return transactionConfiguration;
  }

  protected void setTransactionConfiguration(TransactionConfiguration transactionConfiguration) {
    this.transactionConfiguration = transactionConfiguration;
  }

  public Object assistTransactionContext(ProceedingJoinPoint joinPoint) throws Throwable{ 
    // 获取当前事务
    Transaction currentTransaction = transactionConfiguration.getTransactionManager().getCurrentTransaction();
    // 若事务存在且处于try阶段，则为其设立相应的参与者
    if(currentTransaction != null && currentTransaction.getTransactionStatus().equals(TransactionStatus.TRY)) {
      // 于参数中获取事务上下文，分析其在过程中充当的角色并设立参与者
      TransactionContext transactionContext = TransactionContextHelper.getTransactionContextFromArgs(joinPoint.getArgs());
      TransactionalTCC transactionalTCC = getTransactionalTCC(joinPoint);
      TransactionMethodRole methodRole = TransactionMethodRoleAnalyzer.analyzeMethodRole(transactionContext, transactionalTCC == null ? false : true);
      switch (methodRole) {
        case ORIGINATOR:
        case PROVIDER:
          addOriginatorOrProviderParticipant(joinPoint);
          break;
        case CONSUMER:
          addConsumerParticipant(joinPoint);
          break;
        default:
          break;
      }
    }
    return joinPoint.proceed(joinPoint.getArgs());
  }

  /**
   *  @description 添加消费者
   *  @param joinPoint	AOP切点
   *  @return void
   *  @createTime 2017年1月25日 下午1:07:21 
   *  @author qiudequan
   */
  private Participant addConsumerParticipant(ProceedingJoinPoint joinPoint) {
    // 获取方法中的事务补偿注解，读取其确认方法和取消方法
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();

    // 取当前事务,建立事务XID
    Transaction transaction = transactionConfiguration.getTransactionManager().getCurrentTransaction();
    TransactionXid transactionXid = new TransactionXid(transaction.getXid().getGlobalTransactionId());

    // 获取事务上下文在方法中的位置
    int position = TransactionContextHelper.getTransactionContextPosition(method.getParameterTypes());

    // 根据当前事务，创建事务上下文信息，赋予目标方法中的上下文xinxi
    joinPoint.getArgs()[position] = new TransactionContext(transactionXid, transaction.getTransactionStatus().getStatus());

    Object[] tryMethodArgs = joinPoint.getArgs();
    int length = tryMethodArgs.length;
    Object[] confirmMethodArgs = new Object[length];
    Object[] cancelMethodArgs = new Object[length];

    // try方法参数分别拷贝给确认、取消方法
    System.arraycopy(tryMethodArgs, 0, confirmMethodArgs, 0, length);
    confirmMethodArgs[position] = new TransactionContext(transactionXid, TransactionStatus.CONFIRM.getStatus());

    System.arraycopy(tryMethodArgs, 0, cancelMethodArgs, 0, length);
    cancelMethodArgs[position] = new TransactionContext(transactionXid, TransactionStatus.CANCEL.getStatus());

    // 获取需补偿方法所在类
    Class<?> targetClass = ReflectUtils.getTypeByMethod(joinPoint.getTarget().getClass(), method.getName(), method.getParameterTypes());
    
    // 获取确认、取消方法上下文信息
    ReflectContext confirmReflectContext = new ReflectContext(targetClass, method.getName(), method.getParameterTypes(), confirmMethodArgs);
    ReflectContext cancelReflectContext = new ReflectContext(targetClass, method.getName(), method.getParameterTypes(), cancelMethodArgs);

    // 生成参与者，并加入相应的事务中
    Participant participant = new Participant(transactionXid, new Executor(confirmReflectContext, cancelReflectContext));
    transaction.addParticipant(participant);

    // 更新事务信息
    transactionConfiguration.getTransactionRepository().update(transaction);

    return participant;

  }

  /**
   *  @description 添加发起者或提供者
   *  @param joinPoint  AOP切点
   *  @return void
   *  @createTime 2017年1月25日 下午1:07:57
   *  @author qiudequan
   */
  private Participant addOriginatorOrProviderParticipant(ProceedingJoinPoint joinPoint) {
    // 获取方法中的事务补偿注解，读取其确认方法和取消方法
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    TransactionalTCC transactionalTCC = method.getAnnotation(TransactionalTCC.class);
    String confirm = transactionalTCC.confirm();
    String cancel = transactionalTCC.cancel();

    // 取当前事务,建立事务XID
    Transaction transaction = transactionConfiguration.getTransactionManager().getCurrentTransaction();
    TransactionXid transactionXid = new TransactionXid(transaction.getXid().getGlobalTransactionId());

    // 获取需补偿方法所在类
    Class<?> targetClass = ReflectUtils.getTypeByMethod(joinPoint.getTarget().getClass(), method.getName(), method.getParameterTypes());

    // 获取确认、取消方法上下文信息
    ReflectContext confirmReflectContext = new ReflectContext(targetClass, confirm, method.getParameterTypes(), joinPoint.getArgs());
    ReflectContext cancelReflectContext = new ReflectContext(targetClass, cancel, method.getParameterTypes(), joinPoint.getArgs());

    // 生成参与者，并加入相应的事务中
    Participant participant = new Participant(transactionXid, new Executor(confirmReflectContext, cancelReflectContext));
    transaction.addParticipant(participant);

    // 更新事务信息
    transactionConfiguration.getTransactionRepository().update(transaction);

    return participant;
  }

  /**
   *  @description 获取TransactionalTCC注解信息
   *  @param joinPoint AOP切入点
   *  @return void
   *  @createTime 2017年1月25日 下午12:32:08 
   *  @author qiudequan
   */
  private TransactionalTCC getTransactionalTCC(ProceedingJoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    TransactionalTCC transactionalTCC = method.getAnnotation(TransactionalTCC.class);
    if(transactionalTCC == null){ 
      Method targetMethod = null;
      try {
        targetMethod = joinPoint.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());

        if (targetMethod != null) {
          transactionalTCC = targetMethod.getAnnotation(TransactionalTCC.class);
        }

      } catch (NoSuchMethodException e) {
        transactionalTCC = null;
      }
    }
    return transactionalTCC;
  }
}

package org.spirit.assembled.transaction.tcc.support.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.assembled.transaction.tcc.TransactionContext;
import org.spirit.assembled.transaction.tcc.configuration.TransactionConfiguration;

/**
 * @description 类描述
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
  
  public void interceptorTccMethod(ProceedingJoinPoint joinPoint) {
    TransactionContext transactionContext = getTransactionContext(joinPoint.getArgs());
    if(transactionContext != null) {
    	
    }
  }
  
  public static TransactionContext getTransactionContext(Object[] args) {
    TransactionContext transactionContext = null;
    for (Object object : args) {
      if(object != null && TransactionContext.class.isAssignableFrom(object.getClass())) {
        transactionContext = (TransactionContext) object;
        break;
      }
    }
    return transactionContext;
  } 
  
}

package org.spirit.assembled.transaction.tcc.support.common;

import org.spirit.assembled.transaction.tcc.TransactionContext;

/**
 * @description TCC方法扩展支持类           
 * @author qiudequan
 * @createTime 2017年1月15日 上午10:12:15
 */
public class TCCMethodSupport {
  private TCCMethodSupport() {}
  
  public static void getMethodType(TransactionContext transactionContext, boolean hasTccAnnotation) {
    if(transactionContext == null && hasTccAnnotation) {
      // 没有事务上下文信息，且有tcc事务补偿注解，则为事务发起者(也就是需要进行事务补偿的方法)
      
    } else if(transactionContext == null && !hasTccAnnotation) {
      // 没有事务上下文信息，且没有tcc事务补偿注解，则为
    }
  }
}

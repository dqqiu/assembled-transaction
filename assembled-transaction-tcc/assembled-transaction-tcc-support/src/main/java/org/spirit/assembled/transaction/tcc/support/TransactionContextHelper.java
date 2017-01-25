package org.spirit.assembled.transaction.tcc.support;

import org.spirit.assembled.transaction.tcc.TransactionContext;

/**
 * @description 事务上下文辅助类
 * @author qiudequan
 * @createTime 2017年1月25日 上午11:29:18 
 */
public class TransactionContextHelper {
  private TransactionContextHelper() {}
  
  public static int getTransactionContextPosition(Class<?>[] clzs) {
    int index = -1;     // 未找到返回-1
    for (index = 0; index < clzs.length; index++) {
      if(clzs[index].equals(org.spirit.assembled.transaction.tcc.annotation.TransactionalTCC.class)) {
        break;
      }
    }
    return index;
  }
  
  /**
   *  @description 于参数列表中获取事务上下文
   *  @param args 参数列表
   *  @return TransactionContext 事务上下文信息
   *  @createTime 2017年1月25日 下午12:26:25 
   *  @author qiudequan
   */
  public static TransactionContext getTransactionContextFromArgs(Object[] args) {
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

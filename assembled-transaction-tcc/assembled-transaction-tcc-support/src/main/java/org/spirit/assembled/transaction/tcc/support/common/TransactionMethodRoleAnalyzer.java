package org.spirit.assembled.transaction.tcc.support.common;

import org.spirit.assembled.transaction.tcc.TransactionContext;
import org.spirit.assembled.transaction.tcc.role.TransactionMethodRole;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;

/**
 * @description TCC方法扩展支持类           
 * @author qiudequan
 * @createTime 2017年1月15日 上午10:12:15
 */
public class TransactionMethodRoleAnalyzer {
  private TransactionMethodRoleAnalyzer() {}

  /**
   *  @description 分析方法所充当的角色
   *  @param transactionContext 事务上下文信息
   *  @param hasTccAnnotation 方法中是否有事务补偿注解
   *  @return 方法所充当的角色
   *  @createTime 2017年1月24日 下午5:58:45 
   *  @author qiudequan
   */
  public static TransactionMethodRole analyzeMethodRole(TransactionContext transactionContext, boolean hasTccAnnotation) {
    if(transactionContext == null && hasTccAnnotation) {
      // 没有事务上下文信息，但有tcc事务补偿注解，则为事务发起者(也就是需要进行事务补偿的方法)
      return TransactionMethodRole.ORIGINATOR;
    } else if(transactionContext == null && !hasTccAnnotation) {
      // 没有事务上下文信息，且没有tcc事务补偿注解，则为事务方法消费者
      return TransactionMethodRole.CONSUMER;
    } else if(transactionContext != null && hasTccAnnotation) {
      // 有事务上下文信息，同时有tcc事务补偿注解，则为事务方法提供者
      return TransactionMethodRole.PROVIDER;
    } else {
      // 非上述情况，皆为普通方法
      return TransactionMethodRole.NORMAL;
    }
  }
}

package org.spirit.assembled.transaction.tcc.configuration;

import org.spirit.assembled.transaction.tcc.manager.TransactionManager;
import org.spirit.assembled.transaction.tcc.repository.TransactionRepository;

/**
 * @description 事务配置类
 * @author qiudequan
 * @createTime : 2017年1月6日 下午4:33:06 
 */
public interface TransactionConfiguration {
  /**
   *  @description 获取事务管理器
   *  @return TransactionManager 事务管理器
   *  @createTime 2017年1月10日 下午6:05:25 
   *  @author qiudequan
   */
  TransactionManager getTransactionManager();
  /**
   *  @description 获取事务仓库
   *  @return TransactionManager 事务仓库
   *  @createTime 2017年1月10日 下午6:05:32 
   *  @author qiudequan
   */
  TransactionRepository getTransactionRepository();
}

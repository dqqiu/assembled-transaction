package org.spirit.assembled.transaction.tcc.support.configuration;

import org.spirit.assembled.transaction.tcc.configuration.TransactionConfiguration;
import org.spirit.assembled.transaction.tcc.manager.TransactionManager;
import org.spirit.assembled.transaction.tcc.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description 事务配置信息
 * @author qiudequan
 * @createTime 2017年1月25日 下午2:02:36 
 */
public class DefaultTransactionConfiguration implements TransactionConfiguration {

  @Autowired
  private TransactionRepository transactionRepository;
  
  private TransactionManager transactionManager = new TransactionManager(this);
  
  @Override
  public TransactionManager getTransactionManager() {
    return this.transactionManager;
  }

  @Override
  public TransactionRepository getTransactionRepository() {
    return this.transactionRepository;
  }

}

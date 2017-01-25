package org.spirit.assembled.transaction.tcc.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionApi;
import org.spirit.assembled.transaction.tcc.TransactionContext;
import org.spirit.assembled.transaction.tcc.TransactionStatus;
import org.spirit.assembled.transaction.tcc.TransactionType;
import org.spirit.assembled.transaction.tcc.configuration.TransactionConfiguration;
import org.spirit.assembled.transaction.tcc.exception.CancelException;
import org.spirit.assembled.transaction.tcc.exception.ConfirmException;
import org.spirit.assembled.transaction.tcc.exception.NoExistTransactionException;

/**
 * @description 事务管理器
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:20:06 
 */
public class TransactionManager implements TransactionApi {
  static final Logger LOG = LoggerFactory.getLogger(TransactionManager.class.getSimpleName());
  private TransactionConfiguration transactionConfiguration;
  private ThreadLocal<Transaction> localTransaction = new ThreadLocal<>();
  
  public TransactionManager(TransactionConfiguration transactionConfiguration) {
    this.transactionConfiguration = transactionConfiguration;
  }
  
  public Transaction getCurrentTransaction() {
    return localTransaction.get();
  }
  
  public void begin() {
    Transaction masterTransaction = new Transaction(TransactionType.MASTER);
    transactionConfiguration.getTransactionRepository().create(masterTransaction);
    localTransaction.set(masterTransaction);
  }
  
  public void begin(TransactionContext context) throws NoExistTransactionException {
    // 查找到当前的事务，并开始事务
    Transaction curTransaction = transactionConfiguration.getTransactionRepository().findByXid(context.getXid());
    if(curTransaction != null) {
      curTransaction.setTransactionStatus(TransactionStatus.valueOf(context.getStatus()));
      localTransaction.set(curTransaction);
    } else {
      throw new NoExistTransactionException();
    }
  }
  
  public void newTransaction(TransactionContext context) {
    Transaction curTransaction = new Transaction(context);
    transactionConfiguration.getTransactionRepository().create(curTransaction);
    localTransaction.set(curTransaction);
  }
  
  @Override
  public void commit() {
    Transaction commitTransaction = getCurrentTransaction();
    // 修改为确认状态
    commitTransaction.setTransactionStatus(TransactionStatus.CONFIRM);
    try {
      commitTransaction.commit();
    } catch (Exception e) {
      LOG.error("The transaction confirm failed!", e);
      throw new ConfirmException(e);
    }
    
  }

  @Override
  public void rollback() {
    Transaction commitTransaction = getCurrentTransaction();
    // 修改为取消状态
    commitTransaction.setTransactionStatus(TransactionStatus.CANCEL);
    try {
      commitTransaction.rollback();
    } catch (Exception e) {
      LOG.error("The transaction cancel failed!", e);
      throw new CancelException(e);
    }
  }
}

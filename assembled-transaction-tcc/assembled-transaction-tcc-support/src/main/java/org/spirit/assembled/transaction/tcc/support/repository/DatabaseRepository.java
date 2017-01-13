package org.spirit.assembled.transaction.tcc.support.repository;

import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionXid;

/**
 * @description 基于数据库实现的事务仓库
 * @author qiudequan
 * @createTime 2017年1月11日 下午13:51:29 
 */
public class DatabaseRepository extends AbstractCacheableRepository {

  public DatabaseRepository(String cacheName) {
    super(cacheName);
  }

  @Override
  protected int makeCreate(Transaction transaction) {
    return 0;
  }

  @Override
  protected int makeDelete(Transaction transaction) {
    return 0;
  }

  @Override
  protected int makeUpdate(Transaction transaction) {
    return 0;
  }

  @Override
  protected Transaction makeFindByXid(TransactionXid xid) {
    return null;
  }

}

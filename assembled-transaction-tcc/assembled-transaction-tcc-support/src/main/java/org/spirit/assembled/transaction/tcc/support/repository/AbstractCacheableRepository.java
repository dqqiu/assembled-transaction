package org.spirit.assembled.transaction.tcc.support.repository;

import javax.transaction.xa.Xid;

import org.spirit.assembled.transaction.api.bean.Cache;
import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionXid;
import org.spirit.assembled.transaction.tcc.repository.TransactionRepository;

/**
 * @description 缓存仓库基类
 * @author qiudequan
 * @createTime 2017年1月11日 下午12:21:09 
 */
public abstract class AbstractCacheableRepository implements TransactionRepository {
  // 缓存时间，单位：s
  private static final int EXPIRE = 300;

  // 事务缓存
  private Cache<Xid, Transaction> cache;

  public AbstractCacheableRepository(String cacheName) {
    cache = new Cache<>(cacheName, EXPIRE);
  }

  @Override
  public int create(Transaction transaction) {
    int result = makeCreate(transaction);
    if(result > 0) {
      cache.put(transaction.getXid(), transaction);
    }
    return result;
  }

  @Override
  public int delete(Transaction transaction) {
    int result = makeDelete(transaction);
    if(result > 0) {
      cache.put(transaction.getXid(), transaction);
    }
    return result;
  }

  @Override
  public int update(Transaction transaction) {
    int result = makeUpdate(transaction);
    if(result > 0) {
      cache.put(transaction.getXid(), transaction);
    }
    return 0;
  }

  @Override
  public Transaction findByXid(TransactionXid xid) {
    Transaction transaction = cache.get(xid);
    if(transaction == null) {
      transaction = makeFindByXid(xid);

      if(transaction != null) {
//        cache.put(xid, transaction);
      }
    }
    return transaction;
  }

  /**
   *  @description 处理事务创建过程
   *  @param transaction 事务
   *  @return int 创建结果，大于0则为成功，否则为失败
   *  @createTime 2017年1月11日 下午2:10:58 
   *  @author qiudequan
   */
  protected abstract int makeCreate(Transaction transaction);

  /**
   *  @description 处理事务删除过程
   *  @param transaction 事务
   *  @return int 删除结果，大于0则为成功，否则为失败
   *  @createTime 2017年1月11日 下午2:11:28 
   *  @author qiudequan
   */
  protected abstract int makeDelete(Transaction transaction);

  /**
   *  @description 处理事务更新过程
   *  @param transaction 事务
   *  @return int 更新结果，大于0则为成功，否则为失败
   *  @createTime 2017年1月11日 下午2:11:34 
   *  @author qiudequan
   */
  protected abstract int makeUpdate(Transaction transaction);

  /**
   *  @description 查找事务
   *  @param xid 事务Xid
   *  @return Transaction 查找到的事务
   *  @createTime 2017年1月11日 下午2:11:34 
   *  @author qiudequan
   */
  protected abstract Transaction makeFindByXid(TransactionXid xid);
}

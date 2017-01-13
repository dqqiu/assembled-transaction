package org.spirit.assembled.transaction.tcc.repository;

import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionXid;

/**
 * @description 事务仓库，近乎于dao层
 * @author qiudequan
 * @createTime 2017年1月10日 下午5:55:01 
 */
public interface TransactionRepository {
  /**
   *  @description 新增事务
   *  @param transaction 事务
   *  @return int 更新结果，大于0则为成功，否则为失败
   *  @createTime 2017年1月10日 下午5:55:27 
   *  @author qiudequan
   */
  int create(Transaction transaction);
  
  /**
   *  @description 删除事务
   *  @param transaction 事务
   *  @return int 更新结果，大于0则为成功，否则为失败
   *  @createTime 2017年1月10日 下午5:55:34
   *  @author qiudequan
   */
  int delete(Transaction transaction);
  
  /**
   *  @description 更新事务
   *  @param transaction 事务
   *  @return int 更新结果，大于0则为成功，否则为失败
   *  @createTime 2017年1月10日 下午5:55:52 
   *  @author qiudequan
   */
  int update(Transaction transaction);
  
  /**
   *  @description 查找事务，根据TransactionXid
   *  @param xid 事务Xid
   *  @return Transaction TransactionXid对应的事务
   *  @createTime 2017年1月10日 下午5:56:16
   *  @author qiudequan
   */
  Transaction findByXid(TransactionXid xid);
}

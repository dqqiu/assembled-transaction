package org.spirit.assembled.transaction.tcc;

/**
 * @description 事务接口
 * @author qiudequan
 * @createTime 2017年1月10日 下午3:55:36 
 */
public interface TransactionApi {
  /**
   *  @description 事务提交
   *  @return 
   *  @createTime 2017年1月10日 下午3:57:26 
   *  @author qiudequan
   */
  void commit();
  
  /**
   *  @description 事务回滚
   *  @return 
   *  @createTime 2017年1月10日 下午3:57:42 
   *  @author qiudequan
   */
  void rollback();
}

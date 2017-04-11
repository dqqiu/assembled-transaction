package org.spirit.assembled.transaction.tcc.role;

import java.io.Serializable;

import org.spirit.assembled.transaction.tcc.TransactionApi;
import org.spirit.assembled.transaction.tcc.TransactionXid;

/**
 * @description 参与者，全局可存在多个
 * @author qiudequan
 * @createTime 2017年1月10日 下午1:20:06 
 */
public class Participant implements Serializable, TransactionApi {

  private static final long serialVersionUID = -2122567803379325565L;
  private TransactionXid xid;
  // 事务执行者
  private Executor executor;

  public Participant() {
    // 无参构造
  }

  public Participant(TransactionXid xid, Executor executor) {
    this.xid = xid;
    this.executor = executor;
  }

  public TransactionXid getXid() {
    return xid;
  }

  public void setXid(TransactionXid xid) {
    this.xid = xid;
  }

  public Executor getExecutor() {
    return executor;
  }

  public void setExecutor(Executor executor) {
    this.executor = executor;
  }

  @Override
  public void commit() {
    // 交由事务执行者进行事务提交
    executor.commit();
  }

  @Override
  public void rollback() {
    // 交由事务执行者进行事务回滚
    executor.rollback();
  }

}

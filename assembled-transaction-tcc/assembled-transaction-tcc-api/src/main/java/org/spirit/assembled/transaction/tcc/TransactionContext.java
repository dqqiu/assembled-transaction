package org.spirit.assembled.transaction.tcc;

import java.io.Serializable;

/**
 * @description 事务上下文
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:20:06 
 */
public class TransactionContext implements Serializable {

  private static final long serialVersionUID = -7563792399796462032L;
  private TransactionXid xid; // 实现XA规范的Xid
  private int status; // 事务状态

  public TransactionContext() {
    // 无参构造
  }

  public TransactionContext(TransactionXid xid, int status) {
    this.xid = xid;
    this.status = status;
  }

  public TransactionXid getXid() {
    return xid;
  }

  public void setXid(TransactionXid xid) {
    this.xid = xid;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

}

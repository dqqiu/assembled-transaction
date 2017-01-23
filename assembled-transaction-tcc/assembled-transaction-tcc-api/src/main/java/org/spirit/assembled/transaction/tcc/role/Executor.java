package org.spirit.assembled.transaction.tcc.role;

import java.io.Serializable;

import org.spirit.assembled.transaction.api.bean.ReflectContext;
import org.spirit.assembled.transaction.api.utils.ReflectUtils;
import org.spirit.assembled.transaction.tcc.TransactionApi;

/**
 * @description 执行者
 * @author qiudequan
 * @createTime 2017年1月10日 下午1:20:26 
 */
public class Executor implements Serializable, TransactionApi {

  private static final long serialVersionUID = -8396656794139988176L;
  private ReflectContext confirmContext;
  private ReflectContext cancelContext;

  public Executor() {
    // 无参构造
  }

  public Executor(ReflectContext confirmContext, ReflectContext cancelContext) {
    this.confirmContext = confirmContext;
    this.cancelContext = cancelContext;
  }

  public ReflectContext getConfirmContext() {
    return confirmContext;
  }

  public void setConfirmContext(ReflectContext confirmContext) {
    this.confirmContext = confirmContext;
  }

  public ReflectContext getCancelContext() {
    return cancelContext;
  }

  public void setCancelContext(ReflectContext cancelContext) {
    this.cancelContext = cancelContext;
  }

  @Override
  public void commit() {
    ReflectUtils.invoke(confirmContext);
  }

  @Override
  public void rollback() {
    ReflectUtils.invoke(cancelContext);
  }

}

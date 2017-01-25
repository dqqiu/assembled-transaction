package org.spirit.assembled.transaction.tcc.exception;

/**
 * @description 不存在事务异常
 * @author qiudequan
 * @createTime 2017年1月25日 上午10:36:56 
 */
public class NoExistTransactionException extends Exception {

  private static final long serialVersionUID = 5142910186608847324L;

  private static final String DEFAULT_MESSAGE = "不存在的事务";

  public NoExistTransactionException() {
    super(DEFAULT_MESSAGE);
  }

  public NoExistTransactionException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoExistTransactionException(String message) {
    super(message);
  }

  public NoExistTransactionException(Throwable cause) {
    super(cause);
  }

}

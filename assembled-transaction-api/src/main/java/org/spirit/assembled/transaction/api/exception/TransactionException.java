package org.spirit.assembled.transaction.api.exception;

/**
 * @description 事务异常
 * @author qiudequan
 * @createTime 2017年1月11日 下午4:19:52 
 */
public class TransactionException extends RuntimeException {

  private static final long serialVersionUID = 1342947361443049278L;

  public TransactionException(String message, Throwable cause) {
    super(message, cause);
  }

  public TransactionException(String message) {
    super(message);
  }

  public TransactionException(Throwable cause) {
    super(cause);
  }
  
  
}

package org.spirit.assembled.transaction.tcc.exception;

/**
 * @description 确认阶段异常
 * @author qiudequan
 * @createTime 2017年1月10日 下午5:49:10 
 */
public class ConfirmException extends RuntimeException {

  private static final long serialVersionUID = -5708612344913317413L;

  public ConfirmException() {
    super();
  }

  public ConfirmException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConfirmException(String message) {
    super(message);
  }

  public ConfirmException(Throwable cause) {
    super(cause);
  }

}

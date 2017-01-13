package org.spirit.assembled.transaction.tcc.exception;

/**
 * @description 取消阶段异常
 * @author qiudequan
 * @createTime 2017年1月10日 下午5:49:10 
 */
public class CancelException extends RuntimeException {
      
  private static final long serialVersionUID = -7591991951592094807L;

  public CancelException() {
    super();
  }

  public CancelException(String message, Throwable cause) {
    super(message, cause);
  }

  public CancelException(String message) {
    super(message);
  }

  public CancelException(Throwable cause) {
    super(cause);
  }

}

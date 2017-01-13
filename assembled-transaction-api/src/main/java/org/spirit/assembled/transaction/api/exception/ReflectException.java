package org.spirit.assembled.transaction.api.exception;

/**
 * @description 反射异常(运行时)
 * @author qiudequan
 * @createTime 2017年1月10日 下午4:06:28 
 */
public class ReflectException extends RuntimeException {
  private static final long serialVersionUID = 9192036947432655519L;

  public ReflectException() {
    super();
  }

  public ReflectException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReflectException(String message) {
    super(message);
  }

  public ReflectException(Throwable cause) {
    super(cause);
  }
  
  
}

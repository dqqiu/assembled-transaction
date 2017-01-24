package org.spirit.assembled.transaction.api.common;

import java.io.Serializable;

/**
 * @description 消息响应实体类
 * @author qiudequan
 * @createTime 2017年1月24日 下午1:07:50 
 */
public class Response<T> implements Serializable {
  private static final long serialVersionUID = 7400984062346402239L;

  /**
   * 响应CODE码
   */
  private Integer code;
  /**
   * 响应消息
   */
  private String message;
  /**
   * 响应数据
   */
  private T data;

  public Response() {
    // 无参构造
  }

  public Response(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}

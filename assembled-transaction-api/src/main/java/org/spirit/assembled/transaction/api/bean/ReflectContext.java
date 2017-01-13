package org.spirit.assembled.transaction.api.bean;

import java.io.Serializable;

/**
 * @description 反射调用上下文
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:29:06 
 */
public class ReflectContext implements Serializable {

  private static final long serialVersionUID = -6348397861283857830L;
  // 目标类
  private Class<?> targetClass;
  // 目标方法
  private String targetMethod;
  // 参数类型
  private Class<?>[] params;
  // 参数值
  private Object[] args;
  
  public ReflectContext() {}
  
  public ReflectContext(Class<?> targetClass, String targetMethod, Class<?>[] params, Object[] args) {
    this.targetClass = targetClass;
    this.targetMethod = targetMethod;
    this.params = params;
    this.args = args;
  }

  public Class<?> getTargetClass() {
    return targetClass;
  }
  
  public void setTargetClass(Class<?> targetClass) {
    this.targetClass = targetClass;
  }
  
  public String getTargetMethod() {
    return targetMethod;
  }
  
  public void setTargetMethod(String targetMethod) {
    this.targetMethod = targetMethod;
  }
  
  public Class<?>[] getParams() {
    return params;
  }
  
  public void setParams(Class<?>[] params) {
    this.params = params;
  }
  
  public Object[] getArgs() {
    return args;
  }
  
  public void setArgs(Object[] args) {
    this.args = args;
  }
  
}

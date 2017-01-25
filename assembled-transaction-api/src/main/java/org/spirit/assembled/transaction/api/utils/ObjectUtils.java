package org.spirit.assembled.transaction.api.utils;

/**
 * @description 对象工具类
 * @author qiudequan
 * @createTime 2017年1月25日 上午10:48:41 
 */
public class ObjectUtils {
  private ObjectUtils() {}

  /**
   *  @description 获取相应类型的默认值
   *  @param clz 类型
   *  @return 返回值
   *  @createTime 2017年1月25日 上午10:55:30 
   *  @author qiudequan
   */
  public static Object getDefaultValue(Class<?> clz) {
    if(int.class.equals(clz)) {
      return 0;
    }
    if(float.class.equals(clz)) {
      return 0;
    }
    if(double.class.equals(clz)) {
      return 0;
    }
    if(short.class.equals(clz)) {
      return 0;
    }
    if(long.class.equals(clz)) {
      return 0;
    }
    if(boolean.class.equals(clz)) {
      return false;
    }
    if(byte.class.equals(clz)) {
      return 0;
    }
    if(char.class.equals(clz)) {
      return 0;
    }
    
    return null;
  }
}

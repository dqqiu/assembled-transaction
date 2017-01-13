package org.spirit.assembled.transaction.api.utils;

/**
 * @description 字符串工具类
 * @author qiudequan
 * @createTime : 2017年1月6日 下午2:28:24
 */
public class StringUtils {
  private StringUtils() {}
  
  /**
   *  @description 字符串拼接
   *  @param sources 要进行拼接的字符串数组
   *  @return 拼接后的字符串
   *  @createTime 2017年1月6日 下午2:32:29 
   *  @author qiudequan
   */
  public static String append(String... sources) {
    if(sources == null || sources.length == 0) {
      return "";
    }
    StringBuilder strBuilder = new StringBuilder();
    for (String source : sources) {
      strBuilder.append(source);
    }
    return strBuilder.toString();
  }
  
  /**
   *  @description 判断是否为空
   *  @param source 字符串
   *  @return boolean true：为空，false：不为空
   *  @createTime 2017年1月6日 下午2:32:29 
   *  @author qiudequan
   */
  public static boolean isEmpty(String source) {
    if(source == null || "".equals(source) || "null".equals(source)) {
      return true;
    }
    return false;
  } 
  
  /**
   *  @description 判断是否不为空
   *  @param source 字符串
   *  @return boolean true：不为空，false：为空
   *  @createTime 2017年1月6日 下午2:32:29 
   *  @author qiudequan
   */
  public static boolean isNotEmpty(String source) {
    return !isEmpty(source);
  } 
  
}

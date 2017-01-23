package org.spirit.assembled.transaction.api.utils;

import java.util.Collection;

/**
 * @description 事务状态枚举
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:29:06 
 */
public class CollectionUtils {
  private CollectionUtils() {}
  /**
   *  @description	: 判断集合是否为空
   *  @param collection 集合
   *  @return boolean true：为空；false：不为空
   *  @createTime 2017年1月6日 下午2:31:19 
   *  @author qiudequan
   */
  public static boolean isEmpty(Collection<?> collection) {
    if(collection == null || collection.isEmpty()){
      return true;
    }
    return false;
  }
  
  /**
   *  @description  : 判断集合是否不为空
   *  @param collection 集合
   *  @return boolean true：不为空；false：为空
   *  @createTime 2017年1月6日 下午2:31:19 
   *  @author qiudequan
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
}

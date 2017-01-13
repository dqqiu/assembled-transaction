package org.spirit.assembled.transaction.api.serializer;

/**
 * @description 序列化接口
 * @author qiudequan
 * @createTime 2017年1月12日 下午4:10:28 
 */
public interface Serializer<T> {
  /**
   *  @description 序列化
   *  @param t 对象
   *  @return 序列化后的字节数组
   *  @createTime 2017年1月12日 下午4:11:53 
   *  @author qiudequan
   */
  public byte[] serialize(T t);
  
  /**
   *  @description 反序列化
   *  @param bytes 序列化字节数组
   *  @return 反序列化后所得对象
   *  @createTime 2017年1月12日 下午4:11:53 
   *  @author qiudequan
   */
  public T deserialize(byte[] bytes);
}

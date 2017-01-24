package org.spirit.assembled.transaction.api.serializer;

/**
 * @description 序列化接口
 * @author qiudequan
 * @createTime 2017年1月12日 下午4:10:28 
 */
public abstract class Serializer<T> {
  /**
   *  @description 序列化
   *  @param model 被序列化对象
   *  @return 序列化后的字节数组
   *  @createTime 2017年1月12日 下午4:11:53 
   *  @author qiudequan
   */
  public byte[] serialize(T model) {
    if(model == null) {
      return null;
    }
    return doSerialize(model);
  }

  /**
   *  @description 序列化操作
   *  @param model 被序列化对象
   *  @return byte[] 序列化后的字节数组
   *  @createTime 2017年1月24日 下午3:59:51 
   *  @author qiudequan
   */
  protected abstract byte[] doSerialize(T model);

  /**
   *  @description 反序列化
   *  @param bytes 序列化字节数组
   *  @return 反序列化后所得对象
   *  @createTime 2017年1月12日 下午4:11:53 
   *  @author qiudequan
   */
  public T deserialize(byte[] bytes) {
    if(bytes == null || bytes.length == 0) {
      return null;
    }
    return doDeserialize(bytes);
  }

  /**
   *  @description 反序列化操作
   *  @param bytes 序列化字节数组
   *  @return T 反序列化后所得对象
   *  @createTime 2017年1月24日 下午3:59:57
   *  @author qiudequan
   */
  protected abstract T doDeserialize(byte[] bytes);
}

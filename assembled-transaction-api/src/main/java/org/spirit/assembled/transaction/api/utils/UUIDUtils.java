package org.spirit.assembled.transaction.api.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @description 事务上下文
 * @author qiudequan
 * @createTime : 2017年1月6日 下午4:44:49
 */
public class UUIDUtils {
  private UUIDUtils() {}

  /**
   *  @description 字节数组转UUID
   *  @param bytes 字节数组
   *  @return UUID
   *  @createTime 2017年1月6日 下午4:47:11 
   *  @author qiudequan
   */
  public static UUID byteArray2UUID(byte[] bytes) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    long long1 = byteBuffer.getLong();
    long long2 = byteBuffer.getLong();
    return new UUID(long1, long2);
  }
  /**
   *  @description UUID转字节数组
   *  @param UUID UUID
   *  @return bytes 字节数组
   *  @createTime 2017年1月6日 下午4:47:11 
   *  @author qiudequan
   */
  public static byte[] uuid2ByteArray(UUID uuid){
    ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
    byteBuffer.putLong(uuid.getMostSignificantBits());
    byteBuffer.putLong(uuid.getLeastSignificantBits());
    return byteBuffer.array();
  }
}

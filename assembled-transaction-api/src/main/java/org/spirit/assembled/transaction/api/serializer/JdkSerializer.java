package org.spirit.assembled.transaction.api.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description JDK序列化
 * @author qiudequan
 * @createTime 2017年1月24日 下午3:07:03 
 */
public class JdkSerializer<T> extends Serializer<T> {

  static final Logger LOG = LoggerFactory.getLogger(JdkSerializer.class);

  @Override
  public byte[] doSerialize(T model) {
    if(model == null) {
      return null;
    }
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
    ObjectOutputStream objectOutputStream;
    try {
      objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(model);
      objectOutputStream.flush();
    } catch (IOException e) {
      LOG.error("Failed to serialize object of type : {}" , model.getClass(), e);
      throw new IllegalArgumentException("Failed to serialize object of type : " + model.getClass(), e);
    }
    return byteArrayOutputStream.toByteArray();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T doDeserialize(byte[] bytes) {
    if(bytes == null || bytes.length == 0) {
      return null;
    } 
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
      return (T) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      LOG.error("Failed to deserialize the object", e);
      throw new IllegalArgumentException("Failed to deserialize the object", e);
    }
  }

}

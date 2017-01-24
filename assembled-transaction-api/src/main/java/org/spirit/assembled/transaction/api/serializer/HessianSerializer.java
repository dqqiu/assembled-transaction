package org.spirit.assembled.transaction.api.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * @description Hessian序列化
 * @author qiudequan
 * @createTime 2017年1月24日 下午3:20:52 
 */
public class HessianSerializer<T> extends Serializer<T> {

  static final Logger LOG = LoggerFactory.getLogger(HessianSerializer.class);

  @Override
  public byte[] doSerialize(T model) {
    ByteArrayOutputStream byteArray = null;
    Hessian2Output output = null;
    try {
      byteArray = new ByteArrayOutputStream();
      output = new Hessian2Output(byteArray);
      output.writeObject(model);
      output.close();
    } catch (IOException e) {
      LOG.error("Failed to serialize object of type: " + model.getClass(), e);
      throw new IllegalArgumentException("Failed to serialize object of type: " + model.getClass(), e);
    }
    return byteArray.toByteArray();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T doDeserialize(byte[] bytes) {
    Hessian2Input input = null;
    Object resultObject;
    try {
      input = new Hessian2Input(new ByteArrayInputStream(bytes));
      resultObject = input.readObject();
      input.close();
    } catch (IOException e) {
      LOG.error("Failed to deserialize object", e);
      throw new IllegalArgumentException("Failed to deserialize object", e);
    }
    return (T) resultObject;
  }

}

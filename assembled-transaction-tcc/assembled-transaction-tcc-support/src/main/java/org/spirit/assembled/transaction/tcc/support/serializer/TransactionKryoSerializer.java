package org.spirit.assembled.transaction.tcc.support.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.assembled.transaction.api.common.ReflectContext;
import org.spirit.assembled.transaction.api.serializer.Serializer;
import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionStatus;
import org.spirit.assembled.transaction.tcc.TransactionType;
import org.spirit.assembled.transaction.tcc.TransactionXid;
import org.spirit.assembled.transaction.tcc.role.Executor;
import org.spirit.assembled.transaction.tcc.role.Participant;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @description 事务对象 Kryo 序列化
 * @author qiudequan
 * @createTime 2017年1月24日 下午3:56:25 
 */
public class TransactionKryoSerializer  extends Serializer<Transaction> {

  static final Logger LOG = LoggerFactory.getLogger(TransactionKryoSerializer.class);
  private static Kryo kryo = null;

  static {
    kryo = new Kryo();
    // Kryo序列化，复杂对象内部所使用的其他对象需要同时注册
    kryo.register(Transaction.class);
    kryo.register(TransactionXid.class);
    kryo.register(TransactionStatus.class);
    kryo.register(TransactionType.class);
    kryo.register(Participant.class);
    kryo.register(Executor.class);
    kryo.register(ReflectContext.class);
  }

  @Override
  public byte[] doSerialize(Transaction transaction) {
    Output output = new Output(256, -1);
    kryo.writeObject(output, transaction);
    return output.toBytes();
  }

  @Override
  public Transaction doDeserialize(byte[] bytes) {
    Input input = new Input(bytes);
    return kryo.readObject(input, Transaction.class);
  }

}

package org.spirit.assembled.transaction.tcc;

import java.io.Serializable;
import java.util.UUID;

import javax.transaction.xa.Xid;

import org.spirit.assembled.transaction.api.utils.UUIDUtils;

/**
 * @description 实现XA规范的事务Xid
 * @author qiudequan
 * @createTime : 2017年1月6日 下午2:20:06 
 */
public class TransactionXid implements Xid, Serializable {

  private static final long serialVersionUID = 3859176756464226627L;
  
  private int formatId; // 格式化ID
  private byte[] branchQualifier; // 分支
  private byte[] globalTransactionId; // 全局事务ID
  
  public TransactionXid() {
    // 进行初始化
    branchQualifier = UUIDUtils.uuid2ByteArray(UUID.randomUUID());
    globalTransactionId = UUIDUtils.uuid2ByteArray(UUID.randomUUID());
  }

  @Override
  public byte[] getBranchQualifier() {
    return branchQualifier;
  }

  @Override
  public int getFormatId() {
    return formatId;
  }

  @Override
  public byte[] getGlobalTransactionId() {
    return globalTransactionId;
  }

}

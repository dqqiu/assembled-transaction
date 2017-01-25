package org.spirit.assembled.transaction.tcc;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import javax.transaction.xa.Xid;

import org.spirit.assembled.transaction.api.utils.UUIDUtils;

/**
 * @description 实现XA规范的事务Xid
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:20:06 
 */
public class TransactionXid implements Xid, Serializable {

  private static final long serialVersionUID = 3859176756464226627L;
  
  private int formatId; // 格式化ID
  private byte[] branchQualifier; // 分支
  private byte[] globalTransactionId; // 全局事务ID
  
  public TransactionXid() {
    // 进行初始化
    branchQualifier = UUIDUtils.uuid2ByteArray(UUID.randomUUID());
    System.out.println(UUIDUtils.byteArray2UUID(branchQualifier).toString());
    globalTransactionId = UUIDUtils.uuid2ByteArray(UUID.randomUUID());
    System.out.println(UUIDUtils.byteArray2UUID(globalTransactionId).toString());
  }
  
  public TransactionXid(byte[] globalTransactionId) {
    this.globalTransactionId = globalTransactionId;
    branchQualifier = UUIDUtils.uuid2ByteArray(UUID.randomUUID());
  }
  
  public TransactionXid(byte[] branchQualifier, byte[] globalTransactionId) {
    this.branchQualifier = branchQualifier;
    this.globalTransactionId = globalTransactionId;
  }

  @Override
  public byte[] getBranchQualifier() {
    return branchQualifier;
  }
  
  public void setBranchQualifier(byte[] branchQualifier) {
		this.branchQualifier = branchQualifier;
	}

	public void setGlobalTransactionId(byte[] globalTransactionId) {
		this.globalTransactionId = globalTransactionId;
	}

	@Override
  public int getFormatId() {
    return formatId;
  }

  @Override
  public byte[] getGlobalTransactionId() {
    return globalTransactionId;
  }

  public TransactionXid clone() {
    byte[] cloneBranchQualifier = new byte[branchQualifier.length];
    byte[] cloneGlobalTransactionId = new byte[globalTransactionId.length];

    System.arraycopy(branchQualifier, 0, cloneBranchQualifier, 0, branchQualifier.length);
    System.arraycopy(globalTransactionId, 0, cloneGlobalTransactionId, 0, globalTransactionId.length);

    return new TransactionXid(cloneBranchQualifier, cloneGlobalTransactionId);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(new Object[]{branchQualifier, globalTransactionId, formatId});
  }

  @Override
  public boolean equals(Object obj) {
    // 重写此方法，以便ehcache能够正确获取缓存
    if(this == obj) {
      return true;
    }
    if(obj == null) {
      return false;
    }
    if(this.getClass() != obj.getClass()) {
      return false;
    }
     TransactionXid tx = (TransactionXid) obj;
     if(this.getFormatId() != tx.getFormatId()) {
       return false;
     }
     if(!Arrays.equals(this.getBranchQualifier(), tx.getBranchQualifier())) {
       return false;
     }
     if(!Arrays.equals(this.getGlobalTransactionId(), tx.getGlobalTransactionId())) {
       return false;
     }
     return true;
  }
  
  
  
}

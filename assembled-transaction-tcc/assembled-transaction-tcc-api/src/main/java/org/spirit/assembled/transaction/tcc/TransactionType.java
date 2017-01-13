package org.spirit.assembled.transaction.tcc;

/**
 * @description 事务类型枚举
 * @author qiudequan
 * @createTime : 2017年1月6日 下午4:00:06 
 */
public enum TransactionType {
  MASTER(1),  // 主事务
  BRANCH(2); // 分支事务
  
  private int type;
  private TransactionType(int type) {
    this.type = type;
  }
  public int getType() {
    return type;
  }
  /**
   *  @description 事务类型值转化
   *  @param status 事务类型值
   *  @return 转化后的事务类型
   *  @Creation Date  : 2017年1月6日 下午4:01:15 
   *  @Author         : qiudequan
   */
  public TransactionType valueOf(int type) {
    switch (type) {
      case 1:
        return TransactionType.MASTER;
      case 2:
        return TransactionType.BRANCH;
      default:
        throw new IllegalArgumentException("Transaction type must be within (1, 2)");
    }
  }
}

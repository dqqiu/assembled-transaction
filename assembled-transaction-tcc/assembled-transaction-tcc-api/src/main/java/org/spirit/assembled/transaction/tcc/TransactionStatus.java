package org.spirit.assembled.transaction.tcc;

/**
 * @description 事务状态枚举
 * @author qiudequan
 * @createTime 2017年1月6日 下午2:20:06 
 */
public enum TransactionStatus {
  TRY(1), // 尝试阶段
  CONFIRM(2), // 确认阶段
  CANCEL(3); // 取消阶段

  private int status;
  private TransactionStatus(int status) {
    this.status = status;
  }
  
  public int getStatus() {
    return status;
  }

  /**
   *  @description 事务状态值转化
   *  @param status 事务状态值
   *  @return 转化后的事务状态
   *  @Creation Date  : 2017年1月6日 下午2:36:28 
   *  @Author         : qiudequan
   */
  public static TransactionStatus valueOf(int status) {
    switch (status) {
      case 1:
        return TransactionStatus.TRY;
      case 2:
        return TransactionStatus.CONFIRM;
      case 3:
        return TransactionStatus.CANCEL;
      default:
        throw new IllegalArgumentException("Transaction status must be within (1, 2, 3).");
    }
  }
}

package org.spirit.assembled.transaction.tcc.role;

/**
 * @description 事务方法充当的角色
 * @author qiudequan
 * @createTime 2017年1月24日 下午5:50:50 
 */
public enum TransactionMethodRole {
  /**
   * 事务源
   */
  ORIGINATOR(1),
  /**
   * 提供者
   */
  PROVIDER(2),
  /**
   * 消费者
   */
  CONSUMER(3),
  /**
   * 正常，非以上角色
   */
  NORMAL(4);
  private int role;
  private TransactionMethodRole(int role) {
    this.role = role;
  }

  public int getType() {
    return this.role;
  }

  /**
   * @description 事务方法值转换
   * @author qiudequan
   * @param type 事务方法类型
   * @return 事务方法枚举
   * @createTime 2017年1月15日 下午12:05:57
   */
  public static TransactionMethodRole valueOf(int role){ 
    switch (role) {
      case 1:
        return TransactionMethodRole.ORIGINATOR;
      case 2:
        return TransactionMethodRole.PROVIDER;
      case 3:
        return TransactionMethodRole.CONSUMER;
      case 4:
        return TransactionMethodRole.NORMAL;
      default:
        throw new IllegalArgumentException("Transaction method role must be within(1, 2, 3, 4).");
    }
  }
}

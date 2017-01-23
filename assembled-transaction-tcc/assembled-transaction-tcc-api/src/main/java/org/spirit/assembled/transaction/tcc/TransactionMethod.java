package org.spirit.assembled.transaction.tcc;
/**
 * @description 事务方法类型
 * @author qiudequan
 * @createTime 2017年1月15日 上午10:25:00
 */
public enum TransactionMethod {
	ORIGINATOR(1),
	PROVIDER(2),
	CONSUMER(3),
	NORMAL(4);
	private int type;
	private TransactionMethod(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	/**
	 * @description 事务方法值转换
	 * @author qiudequan
	 * @param type 事务方法类型
	 * @return 事务方法枚举
	 * @createTime 2017年1月15日 下午12:05:57
	 */
	public static TransactionMethod valueOf(int type){ 
		switch (type) {
		case 1:
			return TransactionMethod.ORIGINATOR;
		case 2:
			return TransactionMethod.PROVIDER;
		case 3:
			return TransactionMethod.CONSUMER;
		case 4:
			return TransactionMethod.NORMAL;
		default:
			throw new IllegalArgumentException("Transaction method must be within(1, 2, 3, 4).");
		}
	}
}

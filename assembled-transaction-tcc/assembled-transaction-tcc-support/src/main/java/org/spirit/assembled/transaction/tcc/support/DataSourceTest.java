package org.spirit.assembled.transaction.tcc.support;

import org.spirit.assembled.transaction.api.utils.DateUtils;
import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionType;
import org.spirit.assembled.transaction.tcc.TransactionXid;
import org.spirit.assembled.transaction.tcc.support.repository.DatabaseRepository;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @description 在此处添加描述
 * @author qiudequan
 * @createTime 2017年1月15日 下午1:34:51
 */
public class DataSourceTest {
	public static void main(String[] args) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/TCC");
		DatabaseRepository databaseRepository = new DatabaseRepository("transactionCache");
		databaseRepository.setRegion("TEST");
		databaseRepository.setTableName("TCC_TRANSACTION");
		databaseRepository.setDataSource(dataSource);
		int result = create(databaseRepository);
		if(result > 0) {
			System.out.println("数据插入成功！");
		}
//		TransactionXid xid = new TransactionXid();
//		findByXid(databaseRepository, xid);
	}

	public static int create(DatabaseRepository databaseRepository) {
		Transaction transaction = new Transaction(TransactionType.MASTER);
		transaction.setCreateBy("admin");
		transaction.setCreateTime(DateUtils.getCurrentDate());
		return databaseRepository.create(transaction);
		
	}
	
	public static Transaction findByXid(DatabaseRepository databaseRepository, TransactionXid xid) {
		return databaseRepository.findByXid(xid);
	}
}

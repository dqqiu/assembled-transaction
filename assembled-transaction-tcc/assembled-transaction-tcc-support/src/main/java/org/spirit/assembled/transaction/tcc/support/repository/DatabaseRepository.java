package org.spirit.assembled.transaction.tcc.support.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.assembled.transaction.api.serializer.JdkSerializer;
import org.spirit.assembled.transaction.api.serializer.Serializer;
import org.spirit.assembled.transaction.api.utils.CollectionUtils;
import org.spirit.assembled.transaction.api.utils.DataSourceUtils;
import org.spirit.assembled.transaction.api.utils.StringUtils;
import org.spirit.assembled.transaction.api.utils.UUIDUtils;
import org.spirit.assembled.transaction.tcc.Transaction;
import org.spirit.assembled.transaction.tcc.TransactionXid;

/**
 * @description 基于数据库实现的事务仓库
 * @author qiudequan
 * @createTime 2017年1月11日 下午13:51:29 
 */
public class DatabaseRepository extends AbstractCacheableRepository {
    static final Logger LOG = LoggerFactory.getLogger(DatabaseRepository.class);
	// 数据源
	private DataSource dataSource;
	// 相关表名
	private String tableName;
	// 事务域
	private String region;
	
	private Serializer<Transaction> serializer = new JdkSerializer<>();

	public DatabaseRepository(String cacheName) {
		super(cacheName);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}



	@Override
	protected int makeCreate(Transaction transaction) {
		LOG.debug("===> makeCreate, transaction : " + transaction.toString());
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO ").append(tableName);
		sqlBuilder.append(" (XID, CONTENT, REGION, GLOBAL_XID, BRANCH_QUALIFIER, TRANSACTION_STATUS,");
		sqlBuilder.append(" TRANSACTION_TYPE, RETRY_TIME, CREATE_BY, CREATE_TIME,");
		sqlBuilder.append(" UPDATE_BY, UPDATE_TIME, VERSION) VALUES");
		sqlBuilder.append(" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		Connection connection = DataSourceUtils.getConnection(dataSource);
		int length = 13;
		int index = 0;
		Object[] args = new Object[length];
		transaction.setId(UUIDUtils.get32UUID());
		args[index++] = transaction.getId();
		args[index++] = serializer.serialize(transaction);
		args[index++] = region;
		args[index++] = transaction.getXid().getGlobalTransactionId();
		args[index++] = transaction.getXid().getBranchQualifier();
		args[index++] = transaction.getTransactionStatus().getStatus();
		args[index++] = transaction.getTransactionType().getType();
		args[index++] = transaction.getRetryTime();
		args[index++] = transaction.getCreateBy();
		args[index++] = transaction.getCreateTime();
		args[index++] = transaction.getUpdateBy();
		args[index++] = transaction.getUpdateTime();
		args[index++] = transaction.getVersion();
		
		return DataSourceUtils.executeUpdate(sqlBuilder.toString(), connection, args);
	}

	@Override
	protected int makeDelete(Transaction transaction) {
		LOG.debug("===> makeDelete, transaction : " + transaction.toString());
		
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sqlBuilder.append("DELETE FROM ").append(tableName);
		sqlBuilder.append(" WHERE GLOBAL_XID = ? AND BRANCH_QUALIFIER = ?");
		argsList.add(transaction.getXid().getGlobalTransactionId());
		argsList.add(transaction.getXid().getBranchQualifier());
		if(StringUtils.isNotEmpty(getRegion())) {
			sqlBuilder.append(" AND REGION = ?");
			argsList.add(getRegion());
		}
		Connection connection = DataSourceUtils.getConnection(dataSource);
		return DataSourceUtils.executeUpdate(sqlBuilder.toString(), connection, argsList.toArray());
	}
	
	@Override
	protected int makeUpdate(Transaction transaction) {
		LOG.debug("===> makeUpdate, transaction : " + transaction.toString());
		
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sqlBuilder.append("UPDATE ").append(tableName);
		sqlBuilder.append(" SET CONTENT = ?, TRANSACTION_STATUS = ?, UPDATE_TIME = ?, RETRY_TIME = ?, VERSION = VERSION + 1");
		sqlBuilder.append(" WHERE GLOBAL_XID = ? AND BRANCH_QUALIFIER = ? AND VERSION = ?");
		argsList.add(serializer.serialize(transaction));
		argsList.add(transaction.getTransactionStatus().getStatus());
		argsList.add(transaction.getUpdateTime());
		argsList.add(transaction.getRetryTime());
		argsList.add(transaction.getXid().getGlobalTransactionId());
		argsList.add(transaction.getXid().getBranchQualifier());
		argsList.add(transaction.getVersion());
		if(StringUtils.isNotEmpty(getRegion())) {
			sqlBuilder.append(" AND REGION = ?");
			argsList.add(getRegion());
		}
		
		Connection connection = DataSourceUtils.getConnection(dataSource);
		return DataSourceUtils.executeUpdate(sqlBuilder.toString(), connection, argsList.toArray());
	}

	@Override
	protected Transaction makeFindByXid(TransactionXid xid) {
		LOG.debug("===> makeFindByXid, xid : " + xid.toString());
		
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sqlBuilder.append("SELECT");
		sqlBuilder.append(" XID, CONTENT, REGION, GLOBAL_XID, BRANCH_QUALIFIER, TRANSACTION_STATUS,");
		sqlBuilder.append(" TRANSACTION_TYPE, RETRY_TIME, CREATE_BY, CREATE_TIME,");
		sqlBuilder.append(" UPDATE_BY, UPDATE_TIME, VERSION");
		sqlBuilder.append(" FROM ").append(tableName);
		sqlBuilder.append(" WHERE GLOBAL_XID = ? AND BRANCH_QUALIFIER = ?");
		argsList.add(xid.getGlobalTransactionId());
		argsList.add(xid.getBranchQualifier());
		if(StringUtils.isNotEmpty(getRegion())) {
			sqlBuilder.append(" AND REGION = ?");
			argsList.add(getRegion());
		}
		Connection connection = DataSourceUtils.getConnection(dataSource);
		List<Map<String, Object>> transactionList = DataSourceUtils.executeQuery(sqlBuilder.toString(), connection, argsList.toArray());
		if(CollectionUtils.isEmpty(transactionList)) {
			return null;
		}
		
		Map<String, Object> map = transactionList.get(0);
		return serializer.deserialize((byte[]) map.get("CONTENT"));
	}

}

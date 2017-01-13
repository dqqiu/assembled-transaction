package org.spirit.assembled.transaction.api.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.assembled.transaction.api.exception.TransactionException;

/**
 * @description 数据源处理工具类
 * @author qiudequan
 * @createTime 2017年1月11日 下午4:15:41 
 */
public class DataSourceUtils {
  private static final Logger LOG = LoggerFactory.getLogger(DataSourceUtils.class.getSimpleName());
  private DataSourceUtils() {}

  /**
   *  @description 获取数据库连接
   *  @param dataSource 数据源
   *  @return Connection 数据库连接
   *  @createTime 2017年1月11日 下午4:26:02 
   *  @author qiudequan
   */
  public static Connection getConnection(DataSource dataSource) {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      LOG.error("Access to database connection failed!", e);
      throw new TransactionException("Access to database connection failed!", e);
    }
  }

  /**
   *  @description 关闭数据库连接
   *  @param connection 数据库连接
   *  @return void
   *  @createTime 2017年1月11日 下午4:26:18
   *  @author qiudequan
   */
  public static void closeConnection(Connection connection) {
    try {
      if(connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      LOG.error("An exception occurred while close the connection!", e);
      throw new TransactionException("An exception occurred while close the connection!", e);
    }
  }

  /**
   *  @description 关闭数据库声明
   *  @param stmt 数据库声明
   *  @return void
   *  @createTime 2017年1月11日 下午4:26:39
   *  @author qiudequan
   */
  public static void closeStatement(Statement stmt) {
    try {
      if (stmt != null && !stmt.isClosed()) {
        stmt.close();
      }
    } catch (Exception e) {
      LOG.error("An exception occurred while close the statement!", e);
      throw new TransactionException("An exception occurred while close the statement!", e);
    }
  }

  /**
   *  @description 关闭结果集
   *  @param rs 结果集
   *  @return void
   *  @createTime 2017年1月11日 下午4:26:39
   *  @author qiudequan
   */
  public static void closeResultSet(ResultSet resultSet) {
    try {
      if (resultSet != null && !resultSet.isClosed()) {
        resultSet.close();
      }
    } catch (Exception e) {
      LOG.error("An exception occurred while close the resultSet!", e);
      throw new TransactionException("An exception occurred while close the resultSet!", e);
    }
  }

  /**
   * 
   *  @description 执行更新(包括增删改)
   *  @param sql sql语句
   *  @param connection 数据库连接
   *  @param args 参数数组
   *  @return int 执行结果，大于0：执行成功，否则执行失败
   *  @createTime 2017年1月11日 下午4:43:29 
   *  @author qiudequan
   */
  public static int executeUpdate(String sql, Connection connection, Object[] args) {
    if(StringUtils.isEmpty(sql) || connection == null || ArrayUtils.isEmpty(args)) {
      LOG.warn("Args[statement, connection, arg],existing null parameter, do not execute update!");
      return 0;
    }
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(sql);
      // set parameter
      setParamters(preparedStatement, args);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOG.error("An exception occurred while execute update!");
      throw new TransactionException("An exception occurred while execute update!", e);
    } finally {
      closeStatement(preparedStatement);
      closeConnection(connection);
    }
  }

  /**
   * 
   *  @description 执行查询
   *  @param sql sql语句
   *  @param connection 数据库连接
   *  @param args 参数数组
   *  @return List<Map<String, Object>> 查询结果
   *  @createTime 2017年1月11日 下午4:52:21
   *  @author qiudequan
   */
  public static List<Map<String, Object>> executeQuery(String sql, Connection connection, Object[] args) {
    List<Map<String, Object>> resultList = new ArrayList<>();
    if(StringUtils.isEmpty(sql) || connection == null || ArrayUtils.isEmpty(args)) {
      LOG.warn("Args[statement, connection, arg],existing null parameter, do not execute update!");
      return resultList;
    }
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = connection.prepareStatement(sql);
      // set parameter
      setParamters(preparedStatement, args);
      
      resultSet = preparedStatement.executeQuery();
      
      handleResultSet(resultList, resultSet);
      return resultList;
    } catch (SQLException e) {
      LOG.error("An exception occurred while execute query!");
      throw new TransactionException("An exception occurred while execute query!", e);
    } finally {
      closeResultSet(resultSet);
      closeStatement(preparedStatement);
      closeConnection(connection);
    }
  }
  
  private static void setParamters(PreparedStatement preparedStatement, Object[] args) throws SQLException { 
    for (int i = 0, size = args.length; i < size; i++) {
      preparedStatement.setObject(i + 1, args[i]);
    }
  }
  
  private static void handleResultSet(List<Map<String, Object>> resultList, ResultSet resultSet) throws SQLException {
    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnCount = metaData.getColumnCount();
    while(resultSet.next()) {
      Map<String, Object> map = new HashMap<>();
      for (int i = 1; i < columnCount; i++) {
        String key = metaData.getColumnName(i); // get column name
        Object value = resultSet.getObject(key);
        map.put(key, value);
      }
      resultList.add(map);
    }
  }

}

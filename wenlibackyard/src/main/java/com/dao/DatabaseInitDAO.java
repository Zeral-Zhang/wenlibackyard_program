package com.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service("DatabaseInitDAO")
public class DatabaseInitDAO extends BaseDAO {
	/**
	 * 批量执行sql语句
	 * 
	 * @param sqls
	 * @throws SQLException
	 */
	public void excuteSql(List<String> sqls){		
			try {
				for (String sql : sqls) {
					getCurrentSession().createSQLQuery(sql).executeUpdate();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	/**
	 * 判断是否已经初始化数据库
	 * 
	 * @return
	 */
	public int getTableCount() {
		String sql = "select count(1) from INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=database()";
		Query query = getCurrentSession().createSQLQuery(sql);
		BigInteger result = (BigInteger) query.uniqueResult();
		return result.intValue();
	}

	public String getCurrentDatabase() {
		String sql = "select database()";
		Query query = getCurrentSession().createSQLQuery(sql);
		String database = (String) query.uniqueResult();
		return database;
	}
}

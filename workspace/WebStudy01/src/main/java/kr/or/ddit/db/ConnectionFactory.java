package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *  Design Pattern
 *  1. 생성 패턴 : Singleton pattern, Factory Method pattern, Builder Pattern
 *  2. 구조 패턴 : Adapter[Wrapper] Pattern, Proxy Pattern
 *  3. 행위 패턴 : Template Method Pattern, Command Pattern, Strategy Pattern
 */
public class ConnectionFactory {
	private static String oracleURL;
	private static String oracleUser;
	private static String oraclePassword;
	
	private static DataSource dataSource;
	
	static {
		Properties dbInfo = new Properties();
		try(
			InputStream is = 
				ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/DBInfo.properties");
		){
			dbInfo.load(is);
			oracleURL = dbInfo.getProperty("url");
			oracleUser = dbInfo.getProperty("user");
			oraclePassword = dbInfo.getProperty("password");
//			Class.forName(dbInfo.getProperty("driverClassName"));
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(oracleURL);
			bds.setUsername(oracleUser);
			bds.setPassword(oraclePassword);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize")));
			bds.setMaxWaitMillis(Long.parseLong(dbInfo.getProperty("maxWait")));
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal")));
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle")));
			
			dataSource = bds;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
 	public static Connection getConnection() throws SQLException{
//		return DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
 		return dataSource.getConnection();
	}
}
















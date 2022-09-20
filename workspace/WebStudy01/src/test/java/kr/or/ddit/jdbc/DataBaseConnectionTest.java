package kr.or.ddit.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataBaseConnectionTest {
	
	private Connection oracleConn;
	private Connection mariaDBConn;
	private Statement oracleStmt;
	private Statement mariaDBStmt;

	@BeforeClass
	public static void setUpClass() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() {
		String oracleURL = "jdbc:oracle:thin:@//localhost:1521/XE";
		String oracleUser = "ddit";
		String oraclePassword = "java";
		
		String mariaDBURL = "jdbc:mariadb://localhost:3305/python";
		String mariaDBUser = "root";
		String mariaDBPassword = "python";
		try {
			oracleConn = DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
			mariaDBConn = DriverManager.getConnection(mariaDBURL, mariaDBUser, mariaDBPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOracle() {
		String sql = "SELECT MEM_NAME FROM MEMBER";
		 try {
			oracleStmt = oracleConn.createStatement();
			ResultSet rs = oracleStmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("MEM_NAME"));
			}
			System.out.println(oracleConn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testMariaDB() {
		String sql = "SELECT E_NAME FROM EMP";
		try {
			mariaDBStmt = mariaDBConn.createStatement();
			ResultSet rs = mariaDBStmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				System.out.println(rs.getString("E_NAME"));
			}
			System.out.println(mariaDBConn);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		try {
			if(oracleStmt!=null)
				oracleStmt.close();
			if(oracleConn!=null)
				oracleConn.close();
			if(mariaDBStmt!=null)
				mariaDBStmt.close();
			if(mariaDBConn!=null)
				mariaDBConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDownClass() {
		System.out.println("테스트 종료");
	}
}



















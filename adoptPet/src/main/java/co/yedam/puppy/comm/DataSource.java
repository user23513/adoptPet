package co.yedam.puppy.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	// 인스턴스생성
	private static DataSource dataSource = new DataSource(); 
	private Connection conn;

	 // 남들이 나를 생성하지 못하도록 private 생성자
	private DataSource() {}
	
	// 인스턴스를 리턴한다.
	public static DataSource getInstance() {
		return dataSource;
	} 
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 192.168.0.19 팀장
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.19:1521:xe", "puppy", "puppy");
			System.out.println("DB 연결 성공 ~");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패 ㅜ");
		}
		return conn;
	}
}

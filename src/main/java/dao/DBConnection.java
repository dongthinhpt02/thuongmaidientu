package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Supplier;



public class DBConnection {
	private final String serverName = "LAPTOP-SVAHOS0C";
	private final String dbName = "Nhom11";
	private final String portNumber = "1433";
	private final String instance = "SQLEXPRESS";// MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private final String userID = "sa";
	private final String password = "Kirito123456";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
		url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
		}
	public static void main(String[] args) {
		List<Supplier> listcate = new ArrayList<Supplier>();
		String sql = "Select * from Supplier";
		
		try {
			System.out.println(new DBConnection().getConnection());
			Connection conn = new DBConnection().getConnection();//Kết nối dữ liệu
			PreparedStatement ps = conn.prepareStatement(sql);//Truy vấn dữ liệu
			ResultSet rs = ps.executeQuery();//Chuyển dữ liệu ra view 
			while (rs.next()){
				
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setName(rs.getString("name"));
				listcate.add(supplier);
			}
			System.out.println(listcate);//View nào sẽ nhận dữ liệu
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
}
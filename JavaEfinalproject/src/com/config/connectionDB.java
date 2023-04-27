package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {
	
	public Connection connect;
	public Statement statement;
	public ResultSet rs;
	public ResultSetMetaData rsMetaData;
	public PreparedStatement ps;
	
	public ConnectionDb() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothify", "root", "");
			statement = connect.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getClothesData() {
		try {
			ps = connect.prepareStatement("SELECT * FROM clothes");
			rs = ps.executeQuery();
			rsMetaData = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertClothesData(String nama, int stok, int harga) {
		try {
			ps = connect.prepareStatement("INSERT INTO clothes (cloth_nama, cloth_stok, cloth_harga) VALUES (?,?,?,?)");
			ps.setString(1, nama);
			ps.setString(2, stok);
			ps.setInt(3, harga);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateClothesData(int kode, String nama, int stok, int harga) {
		try {
			ps = connect.prepareStatement("UPDATE clothes SET cloth_nama = ?, cloth_stok = ?, cloth_harga = ?  WHERE cloth_kode = ?");
			ps.setString(1, nama);
			ps.setString(2, stok);
			ps.setString(3, harga);
			ps.setInt(4, kode);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteClothesData(int id) {
		try {
			ps = connect.prepareStatement("DELETE FROM clothes WHERE cloth_kode = ?");
			ps.setInt(1, kode);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MySQLDatabase {

	public MySQLDatabase() {

	}

	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		Scanner sc = new Scanner(System.in);

		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_name", "root", "");

//			if(con == null) {
//				System.out.println("connection failed");
//			}else {
//				System.out.println("connection success");
//			}

			st = con.createStatement();

			System.out.print("Enter student no :");
			int sno = sc.nextInt();
			System.out.print("Enter student name :");
			String sname = sc.next();

			int c = st.executeUpdate("insert into `student` values (" + sno + ", '" + sname + "') ");
			if (c == 0) {
				System.out.println("\ninsertion failed");
			} else {
				System.out.println("\ninsertion success");
			}

			rs = st.executeQuery("select * from student");
			System.out.printf("\n%-5s %-10s", "SNO", "SNAME");
			while (rs.next()) {
				int sNo = rs.getInt("sid");
				String sName = rs.getString("sname");
				System.out.printf("\n%-5s %-10s", sNo, sName);
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

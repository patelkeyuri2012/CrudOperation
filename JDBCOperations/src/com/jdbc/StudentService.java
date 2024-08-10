package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// StudentService class implements all methods of crud operation using JDBC
public class StudentService implements JDBCSevice {

	// default constructor
	public StudentService() {

	}

	StudentDetails stddetail = null;

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	// validateName() method for valid student name
	public boolean validateName(String name) {
		String namePattern = "^[a-zA-Z]+$";

		if (!name.matches(namePattern)) {
			System.out.println("\nEnter only alphabets as student name");
			return false;
		}

		return true;
	}

	// validateEmail() method for valid student email
	public boolean validateEmail(String email) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

		if (!email.matches(emailPattern)) {
			System.out.println("\nEnter email in valid format");
			return false;
		}
		return true;
	}

	// validateCno() method for valid student contact no
	public boolean validateCno(String cno) {
		String cnoPattern = "^[6-9]\\d{9}$";

		if (!cno.matches(cnoPattern)) {
			System.out.println("\nEnter contact no in valid format");
			return false;
		}
		return true;
	}
	
	// isDuplicate() method for verify data is duplicate or not
	public boolean isDuplicate(String data) {
		try {
			ps = con.prepareStatement("SELECT * FROM `student_info` WHERE `std_cno` = ? OR `std_email` = ? ");

			ps.setString(1, data);
			ps.setString(2, data);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return true;
			}
			System.out.println("\nDuplicate data is not allowed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// isEmpty() method for check the table of student details are empty or not
	@Override
	public boolean isEmpty() {
		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM `student_info`");
			while (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// notFound() method for to found the data enter by user are in table or not
	@Override
	public boolean notFound(String data) {
		try {
			ps = con.prepareStatement(
					"SELECT * FROM `student_info` WHERE `std_name` LIKE ? OR `std_cno` LIKE ? OR `std_email` LIKE ? OR `std_add` LIKE ?");

			ps.setString(1, "%" + data + "%");
			ps.setString(2, "%" + data + "%");
			ps.setString(3, "%" + data + "%");
			ps.setString(4, "%" + data + "%");

			rs = ps.executeQuery();

			if (!rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// isConnection() method for check user database server is connected or not
	@Override
	public boolean isConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
			if (con == null) {
				return false;
			} else {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// insertData() method for insert student information
	@Override
	public StudentDetails insertData(String sname, String contactNo, String email, String address) {
		try {
			st = con.createStatement();

			if (!validateName(sname)) {
				return null;
			}
			if (!validateCno(contactNo)) {
				return null;
			}
			if (!validateEmail(email)) {
				return null;
			}
			if (!isDuplicate(contactNo)) {
				return null;
			}
			if (!isDuplicate(email)) {
				return null;
			}

			int cnt = st.executeUpdate(
					"INSERT INTO `student_info` (`std_name`, `std_cno`, `std_email`, `std_add`) VALUES ('" + sname
							+ "', '" + contactNo + "', '" + email + "', '" + address + "')");
			if (cnt == 0) {
				System.out.println("\ninsertion failed...please verify your data");
			} else {
				System.out.println("\nyour data inserted successfully");
				
				showData();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// showData() method for show student information
	@Override
	public void showData() {
		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM `student_info` ORDER BY `std_id`");
			System.out.printf("\n%-5s %-10s %-15s %-20s %-50s", "ID", "Name", "Contact No", "Eamil", "Address");
			while (rs.next()) {
				int sNo = rs.getInt("std_id");
				String sName = rs.getString("std_name");
				String sCno = rs.getString("std_cno");
				String sEmail = rs.getString("std_email");
				String sAddress = rs.getString("std_add");
				System.out.printf("\n%-5s %-10s %-15s %-20s %-50s", sNo, sName, sCno, sEmail, sAddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// searchData() method for search student information
	@Override
	public StudentDetails searchData(String searchData) {
		try {
			ps = con.prepareStatement(
					"SELECT * FROM `student_info` WHERE `std_name` LIKE ? OR `std_cno` LIKE ? OR `std_email` LIKE ? OR `std_add` LIKE ?");

			ps.setString(1, "%" + searchData + "%");
			ps.setString(2, "%" + searchData + "%");
			ps.setString(3, "%" + searchData + "%");
			ps.setString(4, "%" + searchData + "%");

			rs = ps.executeQuery();

			System.out.printf("\n%-5s %-10s %-15s %-20s %-50s", "ID", "Name", "Contact No", "Eamil", "Address");
			while (rs.next()) {
				int sNo = rs.getInt("std_id");
				String sName = rs.getString("std_name");
				String sCno = rs.getString("std_cno");
				String sEmail = rs.getString("std_email");
				String sAddress = rs.getString("std_add");

				System.out.printf("\n%-5s %-10s %-15s %-20s %-50s", sNo, sName, sCno, sEmail, sAddress);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// deleteData() method for delete student information
	@Override
	public void deleteData(int stdid) {

		try {
			ps = con.prepareStatement("DELETE FROM `jdbc`.`student_info` WHERE (`std_id` = ?)");

			ps.setInt(1, stdid);
			ps.executeUpdate();
			System.out.println("\nyour record deleted successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// updateData() method for update student information
	@Override
	public StudentDetails updateData(int stdid, String column, String newData) {
		try {
			switch (column) {
			case "std_name":
				if (!validateName(newData)) {
					return null;
				}
				break;
			case "std_cno":
				if (!validateCno(newData)) {
					return null;
				}
				if (!isDuplicate(newData)) {
					return null;
				}
				break;
			case "std_email":
				if (!validateEmail(newData)) {
					return null;
				}
				if (!isDuplicate(newData)) {
					return null;
				}
				break;
			}
			ps = con.prepareStatement("UPDATE `jdbc`.`student_info` SET `" + column + "` = ? WHERE (`std_id` = ?)");
			ps.setString(1, newData);
			ps.setInt(2, stdid);
			ps.executeUpdate();
			System.out.println("\nYour record updated successfully");
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// close() method for close all the object
	@Override
	public void close() {
		try {
			if (con != null) {
				con.close();
			}
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

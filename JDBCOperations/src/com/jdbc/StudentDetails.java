package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// StudentDetails class show details about students
public class StudentDetails {

	private int stdno;
	private String stdname;
	private String stdcontactNo;
	private String stdemail;
	private String stdaddress;

	// default constructor
	public StudentDetails() {

	}
	
	Scanner sc = new Scanner(System.in);

	// parameterize constructor
	public StudentDetails(int sno, String sname, String contactNo, String email, String address) {
		this.stdno = sno;
		this.stdname = sname;
		this.stdcontactNo = contactNo;
		this.stdemail = email;
		this.stdaddress = address;
	}
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	// isDuplicate() method for verify data is duplicate or not
	public boolean isDuplicate(String data, String column) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
	        ps = con.prepareStatement("SELECT * FROM `student_info` WHERE `" + column + "` = ?");
	        ps.setString(1, data);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the database connections
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	// getter and setter methods which control to access variables of StudentDetails class 
	public int getStdno() {
		return stdno;
	}

	public void setStdno(int stdno) {
		this.stdno = stdno;
	}

	public String getStdname() {
		return stdname;
	}

	public void setStdname(String stdname) {
	    String namePattern = "^[a-zA-Z]+$";

	    while (!stdname.matches(namePattern)) {
	        System.out.print("Enter only alphabets as student name: ");
	        stdname = sc.next();
	    }

	    this.stdname = stdname;
	}

	public String getStdcontactNo() {
		return stdcontactNo;
	}

	public void setStdcontactNo(String stdcontactNo) {
	    String cnoPattern = "^[6-9]\\d{9}$";

	    while (!stdcontactNo.matches(cnoPattern) || isDuplicate(stdcontactNo, "std_cno")) {
	        if (!stdcontactNo.matches(cnoPattern)) {
	            System.out.print("Enter contact no in valid format: ");
	        } else {
	            System.out.print("Duplicate contact no is not allowed\nEnter student contact no: ");
	        }
	        stdcontactNo = sc.next();
	    }
	    this.stdcontactNo = stdcontactNo;
	}

	public String getStdemail() {
		return stdemail;
	}

	public void setStdemail(String stdemail) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

		while (!stdemail.matches(emailPattern) || isDuplicate(stdemail, "std_email")) {
	        if (!stdemail.matches(emailPattern)) {
	            System.out.print("Enter email in valid format: ");
	        } else {
	            System.out.print("Duplicate email is not allowed\nEnter student email: ");
	        }
	        stdemail = sc.next();
	    }
		this.stdemail = stdemail;
	}

	public String getStdaddress() {
		return stdaddress;
	}
	
	public void setStdaddress(String stdaddress) {
	    this.stdaddress = stdaddress;
	}

}

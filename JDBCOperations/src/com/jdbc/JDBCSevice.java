package com.jdbc;

// JDBCSevice interface that have all methods of crud operation using JDBC
public interface JDBCSevice {
	public boolean isEmpty();
	public boolean notFound(String data); 
	public boolean isConnection();
	public StudentDetails insertData(String sname, String contactNo, String email, String address);
	public void showData();
	public StudentDetails searchData(String searchData);
	public void deleteData(int stdid);
	public StudentDetails updateData(int stdid, String newData, String column);
	public void close();
}
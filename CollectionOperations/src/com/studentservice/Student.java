package com.studentservice;


public class Student {
	
	private int sno;
	private String sname;
	private String email;
	private String contactNo;
	private String address; 

	public Student() {
		
	}
	
	public Student(int sno, String sname, String email, String contactNo, String address) {
		this.sno = sno;
		this.sname = sname;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

package com.studentservice;

public interface Service {
	public boolean isEmpty();
	public Student add(String name, String email, String contactNo, String address);
	public void show();
	public Student search(String data);
	public void delete(String data);
	public Student update(int no, String stddata, String newData);
}

package com.studentservice;

import java.util.ArrayList;

public class StudentService implements Service {

	public StudentService() {

	}

	private ArrayList<Student> studentList = new ArrayList<>();
	Student student = null;
	
	public boolean validateName(String name) {
		String namePattern = "^[a-zA-Z]+$";

		if (!name.matches(namePattern)) {
			System.out.println("\nEnter valid name");
			return false;
		}
		return true;
	}

	public boolean validateEmail(String email) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

		if (!email.matches(emailPattern)) {
			System.out.println("\nEnter valid email");
			return false;
		}

		for (Student existingStudent : studentList) {
			if (existingStudent.getEmail().equals(email)) {
				System.out.println("\nDuplicate email is not allowed");
				return false;
			}
		}
		return true;
	}

	public boolean validateCno(String cno) {
		String cnoPattern = "^[0-9]{10}$";

		if (!cno.matches(cnoPattern)) {
			System.out.println("\nEnter valid contact no");
			return false;
		}

		for (Student existingStudent : studentList) {
			if (existingStudent.getContactNo().equals(cno)) {
				System.out.println("\nDuplicate contact no is not allowed");
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return studentList.size() == 0;
	}

	private int sno = 1;

	@Override
	public Student add(String name, String email, String contactNo, String address) {
		student = new Student();
		if (!validateName(name)) {
			return null;
		}
		if (!validateEmail(email)) {
			return null;
		}
		if (!validateCno(contactNo)) {
			return null;
		}
		student.setSno(sno++);
		student.setSname(name);
		student.setEmail(email);
		student.setContactNo(contactNo);
		student.setAddress(address);
		studentList.add(student);
		System.out.println("\nData inserted");
		
		return student;
	}

	@Override
	public void show() {
		System.out.printf("\n%-5s %-15s %-20s %-15s %-30s\n", "SNO", "SNAME", "EMAIL", "CONTACT NO", "ADDRESS");
		for (Student student : studentList) {
			System.out.printf("%-5s %-15s %-20s %-15s %-30s\n", student.getSno(), student.getSname(),
					student.getEmail(), student.getContactNo(), student.getAddress());
		}
	}

	@Override
	public Student search(String data) {
		boolean isInteger = true;
		try {
			Integer.parseInt(data);
		} catch (NumberFormatException e) {
			isInteger = false;
		}
		if (isInteger) {
			int searchData = Integer.parseInt(data);
			for (Student student : studentList) {
				if (student.getSno() == searchData) {
					return student;
				}
			}
		} else {
			for (Student student : studentList) {
				if (student.getSname().equals(data) || student.getEmail().equals(data)
						|| student.getContactNo().equals(data) || student.getAddress().equals(data)) {
					return student;
				}
			}
		}
		return null;
	}

	@Override
	public void delete(String data) {
		boolean isInteger = true;
		try {
			Integer.parseInt(data);
		} catch (NumberFormatException e) {
			isInteger = false;
		}
		if (isInteger) {
			int removeData = Integer.parseInt(data);
			for (Student student : studentList) {
				if (student.getSno() == removeData) {
					studentList.remove(student);
					System.out.println("\nData removed");
					return;
				}
			}
		} else {
			for (Student student : studentList) {
				if (student.getSname().equals(data)) {
					studentList.remove(student);
					System.out.println("\nData removed");
					return;
				}
			}
		}
	}

	@Override
	public Student update(int no, String stddata, String newData) {
		for (Student student : studentList) {
			if (student.getSno() == no) {
				switch (stddata) {
				case "SNAME":
					if (!validateName(newData)) {
						return null;
					}
					student.setSname(newData);
					break;
				case "EMAIL":
					if (!validateEmail(newData)) {
						return null;
					}
					student.setEmail(newData);
					break;
				case "CONTACTNO":
					if (!validateCno(newData)) {
						return null;
					}
					student.setContactNo(newData);
					break;
				case "ADDRESS":
					student.setAddress(newData);
					break;
				default:
					return null;
				}
				System.out.println("\nData updated");
				return student;
			}
		}
		return null;
	}
}
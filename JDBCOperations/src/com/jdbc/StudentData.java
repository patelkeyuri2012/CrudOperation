package com.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

// StudentData class call methods of crud operation using JDBC which created in StudentService class
public class StudentData {

	// default constructor
	public StudentData() {

	}
	
	// studentData() method for managing student infromation
	public void studentData() throws ClassNotFoundException, SQLException {

		JDBCSevice stdsevice = new StudentService();
		StudentDetails sd = new StudentDetails();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println(
					"\n\n1. add student into student table\n2. show student table \n3. search student from student table\n4. delete student from student table\n5. update student from student table\n0. exit");
			System.out.print("\nEnter your choice: ");
			int ch = sc.nextInt();

			switch (ch) {
			case 1:
				// add student information
				if (stdsevice.isConnection() == true) {
					System.out.print("\nEnter student name: ");
					String stdname = sc.next();
					sd.setStdname(stdname);
					System.out.print("Enter student contact no: ");
					String stdcno = sc.next();
					sd.setStdcontactNo(stdcno);
					System.out.print("Enter student email: ");
					String stdemail = sc.next();
					sd.setStdemail(stdemail);
					System.out.print("Enter student address: ");
					String stdaddress = sc.next();
					stdsevice.insertData(stdname, stdcno, stdemail, stdaddress);
				}
				break;
			case 2:
				// show student information
				if (stdsevice.isConnection() == true) {
					if (stdsevice.isEmpty() == true) {
						System.out.println("\nStudent table is empty");
					} else {
						stdsevice.showData();
					}
				}
				break;
			case 3:
				// search student information
				if (stdsevice.isConnection() == true) {

					if (stdsevice.isEmpty() == true) {
						System.out.println("\nStudent table is empty");
					} else {
						System.out.print("\nEnter student data to search: ");
						String searchData = sc.next();
						if (stdsevice.notFound(searchData)) {
							System.out.println("\nStudent with " + searchData + " is not found");
						} else {
							stdsevice.searchData(searchData);
						}
					}
				}
				break;
			case 4:
				// delete student information
				if (stdsevice.isConnection() == true) {

					if (stdsevice.isEmpty() == true) {
						System.out.println("\nStudent table is empty");
					} else {
						System.out.print("\nEnter student id to delete: ");
						int stdid = sc.nextInt();
						if (stdsevice.notFound(String.valueOf(stdid))) {
							System.out.println("\nStudent with " + stdid + " is not found");
						} else {
							stdsevice.deleteData(stdid);
							stdsevice.showData();
						}
					}
				}
				break;
			case 5:
				// update student information
				if (stdsevice.isConnection() == true) {

					if (stdsevice.isEmpty() == true) {
						System.out.println("\nStudent table is empty");
					} else {

						System.out.print("\nEnter student id to update: ");
						int stdId = sc.nextInt();
						if (stdsevice.notFound(String.valueOf(stdId))) { 
							System.out.println("\nStudent with " + stdId + " is not found");						    
						} else {
						System.out.print("\nEnter column to update\nstd_name, std_cno, std_email, std_add: ");
						String column = sc.next();
						System.out.print("\nEnter student data to update into " + column + " column: ");
						String newData = sc.next();
						stdsevice.updateData(stdId, column, newData);
						stdsevice.showData();
						}

					}
				}
				break;
			case 0:
				// exit
				stdsevice.close();
				System.exit(0);
			default:
				System.out.println("\nInvalid choice");
			}
		}
	}
}

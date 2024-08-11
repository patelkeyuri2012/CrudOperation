package com.studentservice;

import java.util.Scanner;

public class StudentData {
	public StudentData() {

	}

	public void studentData() {
		Scanner sc = new Scanner(System.in);
		Service stdService = new StudentService();

		while (true) {
			System.out.println(
					"\n1. add student from student list\n2. show student list \n3. search student from student list\n4. delete student from student list\n5. update student from student list\n0. exit");
			System.out.print("\nEnter your choice: ");
			int ch = sc.nextInt();

			switch (ch) {
			case 1:
				System.out.print("\nEnter student name: ");
				String name = sc.next();
				System.out.print("Enter student email: ");
				String email = sc.next();
				System.out.print("Enter student contact number: ");
				String cno = sc.next();
				System.out.print("Enter student address: ");
				String address = sc.next();
				stdService.add(name, email, cno, address);
				break;
			case 2:
				if (stdService.isEmpty()) {
					System.out.println("\nStudent data is empty");
				} else {
					stdService.show();
				}
				break;
			case 3:
				if (stdService.isEmpty()) {
					System.out.println("\nStudent data is empty");
				} else {
					System.out.print("\nEnter student data to search from list: ");
					String data = sc.next();
					Student std = stdService.search(data);
					if (std != null) {
						System.out.printf("\n%-5s %-15s %-20s %-15s %-30s\n", "SNO", "SNAME", "EMAIL", "CONTACT NO",
								"ADDRESS");
						System.out.printf("%-5s %-15s %-20s %-15s %-30s\n", stdService.search(data).getSno(),
								stdService.search(data).getSname(), stdService.search(data).getEmail(),
								stdService.search(data).getContactNo(), stdService.search(data).getAddress());
					} else {
						System.out.println("\nStudent with " + data + " is not found");
					}
				}
				break;
			case 4:
				if (stdService.isEmpty()) {
					System.out.println("\nStudent data is empty");
				} else {
					System.out.print("\nEnter student no/name to remove from list: ");
					String stdData = sc.next();
					stdService.delete(stdData);
				}
				break;
			case 5:
				if (stdService.isEmpty()) {
					System.out.println("\nStudent data is empty");
				} else {
					System.out.print("\nEnter student no to update from list: ");
					int stdno = sc.nextInt();
					if (stdService.search(String.valueOf(stdno)) != null) {
						System.out.print("\nWhat you want to update?\nSNAME, EMAIL, CONTACTNO, ADDRESS: ");
						String stddata = sc.next();
						switch (stddata) {
						case "SNAME":
							System.out.print("\nEnter new student name: ");
							String newName = sc.next();
							stdService.update(stdno, "SNAME", newName);
							break;
						case "EMAIL":
							System.out.print("\nEnter new student email: ");
							String newEmail = sc.next();
							stdService.update(stdno, "EMAIL", newEmail);
							break;
						case "CONTACTNO":
							System.out.print("\nEnter new student contact no: ");
							String newCno = sc.next();
							stdService.update(stdno, "CONTACTNO", newCno);
							break;
						case "ADDRESS":
							System.out.print("\nEnter new student address: ");
							String newAddress = sc.next();
							stdService.update(stdno, "ADDRESS", newAddress);
							break;
						default:
							System.out.println(stddata + " is not a valid column");
						}
					} else {
						System.out.println("\nStudent with " + stdno + " no is not found");
					}
				}

				break;
			case 0:
				sc.close();
				System.exit(0);
			default:
				System.out.println("\nInvalid choice");
			}
		}
	}
}

package com.jdbc;

import java.sql.SQLException;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		StudentData stdser = new StudentData();
		try {
			stdser.studentData();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Enter valid input");
			return;
		}
	}
}

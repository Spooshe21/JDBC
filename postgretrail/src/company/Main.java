/********************************************************************************************
 *   COPYRIGHT (C) 2024 CREVAVI TECHNOLOGIES PVT LTD
 *   The reproduction, transmission or use of this document/file or its
 *   contents is not permitted without written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *---------------------------------------------------------------------------
 *   Purpose:  Main.java file
 *   Project:  Database Operations
 *   Platform: Cross-platform (Windows, macOS, Linux)
 *   Compiler: JDK-22
 *   IDE:  	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *	           Version: 2024-03 (4.31.0)
 *             Build id: 20240307-1437
 ********************************************************************************************/

package company;

import java.sql.Connection;

/**
 * Main class to demonstrate database operations.
 */
public class Main {

	/**
	 * The main method to execute the database operations.
	 * 
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		DbFunctions db = new DbFunctions();
		Connection conn = db.connectToDb("tutdb", "postgres", "Crevavi");
		db.createTable(conn, "employee");
		db.insert_row(conn, "employee", "swathi", "India");
		// db.update_name(conn, "employee", "sonu", "monu");
		// db.search_by_name(conn, "employee", "monu");
		db.read_data(conn, "employee");
		// db.search_by_id(conn, "employee", 2);
		// db.delete_row_by_name(conn, "employee", "spoorthi");
	}
}

/********************************************************************************************
 *   COPYRIGHT (C) 2024 CREVAVI TECHNOLOGIES PVT LTD
 *   The reproduction, transmission or use of this document/file or its
 *   contents is not permitted without written authorization.
 *   Offenders will be liable for damages. All rights reserved.
 *---------------------------------------------------------------------------
 *   Purpose:  DbFunctions.java
 *   Project:  Database Operations
 *   Platform: Cross-platform (Windows, macOS, Linux)
 *   Compiler: JDK-22
 *   IDE:  	   Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
 *	           Version: 2024-03 (4.31.0)
 *             Build id: 20240307-1437
 ********************************************************************************************/

package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides functions to interact with a PostgreSQL database.
 */
public class DbFunctions {

	/**
	 * Connects to the PostgreSQL database.
	 * 
	 * @param dbname The name of the database.
	 * @param user   The username for authentication.
	 * @param pass   The password for authentication.
	 * @return A Connection object representing the database connection.
	 */
	public Connection connectToDb(String dbname, String user, String pass) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
			if (conn != null) {
				System.out.println("Connection Established");
			} else {
				System.out.println("Connection Failed");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

	/**
	 * Creates a table in the database.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table to be created.
	 */
	public void createTable(Connection conn, String table_name) {
		Statement statement;
		try {
			String query = "create table " + table_name
					+ "(empid SERIAL,name varchar(200),address varchar(200),primary key(empid));";
			statement = conn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Table Created");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Inserts a new row into the specified table with the provided name and
	 * address.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table where the row will be inserted.
	 * @param name       The name to be inserted into the table.
	 * @param address    The address to be inserted into the table.
	 */
	public void insert_row(Connection conn, String table_name, String name, String address) {
		Statement statement;
		try {
			String query = String.format("insert into %s(name,address)values('%s','%s');", table_name, name, address);
			statement = conn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Row Inserted");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Reads data from the specified table in the database connection and prints the
	 * results.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table from which data will be read.
	 */
	public void read_data(Connection conn, String table_name) {
		Statement statement;
		ResultSet rs = null;

		try {
			String query = String.format("select * from %s", table_name);
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				System.out.print(rs.getString("empid") + " ");
				System.out.print(rs.getString("name") + " ");
				System.out.println(rs.getString("address") + " ");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Updates the name in the specified table in the database connection.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table where the update will occur.
	 * @param old_name   The old name to be replaced.
	 * @param new_name   The new name to replace the old name.
	 */
	public void update_name(Connection conn, String table_name, String old_name, String new_name) {
		Statement statement;
		try {
			String query = String.format("update %s set name='%s' where name='%s'", table_name, new_name, old_name);
			statement = conn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Data updated");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Searches for a record in the specified table by the given ID.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table to search.
	 * @param id         The ID of the record to search for.
	 */
	public void search_by_id(Connection conn, String table_name, int id) {
		Statement statement;
		ResultSet rs = null;
		try {
			String query = String.format("select * from %s where empid=%s", table_name, id);
			statement = conn.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				System.out.print(rs.getString("empid") + " ");
				System.out.print(rs.getString("name") + " ");
				System.out.println(rs.getString("address"));

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletes a row from the specified table by the given name.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table from which to delete the row.
	 * @param name       The name of the row to delete.
	 */
	public void delete_row_by_name(Connection conn, String table_name, String name) {
		Statement statement;
		try {
			String query = String.format("delete from %s where name='%s'", table_name, name);
			statement = conn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Data deleted");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletes the specified table from the database.
	 * 
	 * @param conn       The database connection.
	 * @param table_name The name of the table to delete.
	 */
	public void delete_table(Connection conn, String table_name) {
		Statement statement;
		try {
			String query = String.format("drop table %s", table_name);
			statement = conn.createStatement();
			statement.execute(query);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

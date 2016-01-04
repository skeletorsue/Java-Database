package com.skeletorsue;

import org.ini4j.Profile;

import java.sql.*;

public class Database {
	public ConnectionInfo conn_info;

	private Connection conn;
	private Statement statement;

	public Database() {
		conn_info = new ConnectionInfo();
	}

	public Database(Profile.Section Info) {
		conn_info = new ConnectionInfo(Info);
	}

	public Database(ConnectionInfo conn) {
		conn_info = conn;
	}

	/**
	 * Internal function to return the DB connection
	 *
	 * @return
	 * @throws SQLException
	 */
	private Connection connect() throws SQLException {
		if (this.conn instanceof Connection)
			return this.conn;

		try {
			Class.forName(conn_info.DriverClass()).newInstance();
			this.conn = DriverManager.getConnection(build_dsn(), conn_info.User, conn_info.Password);
		} catch (Exception sqle) {
			throw new SQLException(sqle.getMessage());
		}

		return this.conn;
	}

	/**
	 * Builds the dsn string for use when creating the database connection
	 *
	 * @return
	 */
	private String build_dsn() {
		String response = "jdbc:" + conn_info.Driver + ":";

		if (conn_info.Driver == "sqlite") {
		} else {
			response += "//" + conn_info.Host + ":" + conn_info.Port + "/";
		}

		if (conn_info.Name != null) {
			response += conn_info.Name;
		}

		return response;
	}

	/**
	 * Method to run a sql query
	 *
	 * @param query String The query to be executed
	 * @return a ResultSet object containing the results or null if not available
	 * @throws SQLException
	 */
	public ResultSet query(String query) throws SQLException {
		statement = this.connect().createStatement();
		ResultSet res = statement.executeQuery(query);
		return res;
	}

	/**
	 * Method to return a prepared statement
	 *
	 * @param query String The query to be prepared
	 * @return a PreparedStatement that can have parameters bound to it and then executed
	 * @throws SQLException
	 */
	public PreparedStatement prepare(String query) throws SQLException {
		return this.connect().prepareStatement(query);
	}

	/**
	 * Method to insert data to a table
	 *
	 * @param insertQuery String The Insert query
	 * @return boolean
	 * @throws SQLException
	 */
	public int insert(String insertQuery) throws SQLException {
		statement = this.connect().createStatement();
		int result = statement.executeUpdate(insertQuery);
		return result;

	}

}

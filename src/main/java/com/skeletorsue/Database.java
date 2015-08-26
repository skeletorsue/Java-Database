package com.skeletorsue;

import org.ini4j.Profile;

import java.sql.*;

public class Database {
    public ConnectionInfo conn_info;

    private Connection conn;
    private Statement statement;

    public Database () {
        conn_info = new ConnectionInfo();
    }

    public Database (Profile.Section Info) {
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
            this.conn = DriverManager.getConnection("jdbc:" + conn_info.Driver + "://" + conn_info.Host + ":" + conn_info.Port + "/" + conn_info.Name, conn_info.User, conn_info.Password);
        } catch (Exception sqle) {
            throw new SQLException(sqle.getMessage());
        }

        return this.conn;
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

package com.skeletorsue;

import java.sql.*;

public class Database {
    public String Label;

    private Connection conn;
    private Statement statement;
    private String Host, User, Password, Name, Driver, DriverClass;
    private Integer Port;

    public Database(ConnectionInfo conn) {
        Label = conn.Label;
        Host = conn.Host;
        Port = conn.Port;
        User = conn.User;
        Password = conn.Password;
        Name = conn.Name;
        Driver = conn.Driver;
        DriverClass = conn.DriverClass();
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
            Class.forName(this.DriverClass).newInstance();
            this.conn = DriverManager.getConnection("jdbc:" + this.Driver + "://" + this.Host + ":" + this.Port + "/" + this.Name, this.User, this.Password);
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

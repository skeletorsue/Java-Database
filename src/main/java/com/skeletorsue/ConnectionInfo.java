package com.skeletorsue;

import org.ini4j.Profile;

public final class ConnectionInfo {
    public String Host, User, Password, Name, Driver, DriverClass, Label;
    public Integer Port;

    public ConnectionInfo() {
        LoadDefaults();
    }

    public ConnectionInfo(Profile.Section Info) {
        LoadDefaults();
        String tmpString;
        Integer tmpInteger;

        tmpString = Info.get("Host");
        if (tmpString != null)
            Host = tmpString;

        tmpString = Info.get("User");
        if (tmpString != null)
            User = tmpString;

        tmpString = Info.get("Password");
        if (tmpString != null)
            Password = tmpString;

        tmpString = Info.get("Name");
        if (tmpString != null)
            Name = tmpString;

        tmpString = Info.get("Driver");
        if (tmpString != null)
            Driver = tmpString;

        try {
            tmpInteger = Integer.parseInt(Info.get("Port"));
            if (tmpInteger != null)
                Port = tmpInteger;
        } catch (NumberFormatException nfe) {
        }

        tmpString = Info.get("Label");
        if (tmpString != null)
            Label = tmpString;

        tmpString = Info.get("DriverClass");
        if (tmpString != null)
            DriverClass = tmpString;

    }

    public String DriverClass() {

        if (DriverClass != null) {
            return DriverClass;
        }

        return "com." + Driver + ".jdbc.Driver";
    }

    private void LoadDefaults() {
        Host = "localhost";
        User = "root";
        Password = "password";
        Name = "DB";
        Driver = "mysql";
        Port = 3306;
        Label = "Database Connection";

    }
}

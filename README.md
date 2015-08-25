# Java Database Wrapper
Just a simple project to standardize the way my Java projects handle database connections. 

### Example Usage
The connection object supports just passing a Profile.Section from ini4j, or you can set the connection parameters manually.
#### Manually Setting Parameters
	ConnectionInfo conn_info = new ConnectionInfo();
	conn_info.Host = "localhost";
	conn_info.User = "root";
	conn_info.Password = "password";
	conn_info.Name = "Test";
	Database conn = new Database(conn_info);
            
#### Using ini4j.Profile.Section object
	Ini config = new Ini(new File("config.ini"));
	ConnectionInfo conn_info = new ConnectionInfo(config.get("Database_Info"));
	Database conn = new Database(conn_info);

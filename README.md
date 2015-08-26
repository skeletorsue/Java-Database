# Java Database Wrapper
Just a simple project to standardize the way my Java projects handle database connections. 

### Example Usage
The connection object supports just passing a Profile.Section from ini4j, or you can set the connection parameters manually.
#### Manually Setting Parameters
	Database conn = new Database();
	conn.conn_info.Host = "localhost";
	conn.conn_info.User = "root";
	conn.conn_info.Password = "password";
	conn.conn_info.Name = "Test";
            
#### Using ini4j.Profile.Section object
	Ini config = new Ini(new File("config.ini"));
	Database conn = new Database(config.get("Database_Info");

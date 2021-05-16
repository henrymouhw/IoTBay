package iot.models.dao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Think
 */
public class DBConnector extends DB
{
    public DBConnector() throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);
        conn = DriverManager.getConnection(URL+db, dbuser, dbpass);   
    }
    
    public Connection openConnection()
    {
        return this.conn;
    }
    
    public void closeConnection() throws SQLException
    {
        this.conn.close();
    }
    
}

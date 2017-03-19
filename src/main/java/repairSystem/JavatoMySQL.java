package repairSystem;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Алексей on 19.03.2017.
 */
public class JavatoMySQL {

    private static String url;
    private static String login;
    private static String pass;

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    private static void SetDBInfo()
    {
        FileInputStream fis;
        Properties pr = new Properties();
        try{
            fis = new FileInputStream("src/main/resources/db.properties");
            pr.load(fis);
            url = pr.getProperty("db.host");
            login = pr.getProperty("db.login");
            pass = pr.getProperty("db.pass");
        }
        catch(IOException e){
            System.err.println("Error with property file");
        }
    }

    public static ResultSet OpenConnectionAndExecuteQuery(String query)
    {
        SetDBInfo();
        try{
            Connection con = null;
            con = DriverManager.getConnection(url, login, pass);
        }
        catch (SQLException e){
            System.err.println("Error with sql connection");
        }
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch(SQLException e){
            System.err.println("Error with execute query");
        }
        return rs;
    }

    public static boolean CloseConnection() {
        try {
            con.close();
            stmt.close();
            rs.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Error with close connection");
            return false;
        }
    }

}

package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB_Util {

    public static final String URL = "jdbc:mysql://localhost:3306/BankDB";
    public static final String USER = "root";
    public static final String PASSWORD = "985568_Fedora";

public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL,USER, PASSWORD);
}
public static Connection getConnection1() throws SQLException{
    return DriverManager.getConnection(URL,USER,PASSWORD);
}

    public static Connection getConnection2() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}

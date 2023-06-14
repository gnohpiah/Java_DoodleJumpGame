package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public Connection getConnect(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/highscore";
        String user = "root";
        String password ="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

        }catch (Exception er){
            er.printStackTrace();
        }
        return con;
    }
}

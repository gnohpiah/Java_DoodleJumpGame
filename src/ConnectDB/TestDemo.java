package ConnectDB;

import java.sql.Connection;

public class TestDemo {
    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnect();
        System.out.println(connection);
    }
}

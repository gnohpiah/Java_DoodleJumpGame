package ConnectDB;

import java.sql.*;

public class ScoreCheckDA {
    private Connection connection;
    private ConnectDB connectDB;
    private ResultSet resultSet;
    private Statement statement;
    public ScoreCheckDA() {
        connectDB = new ConnectDB();
        this.deleteExceptTop();
    }

    public void insert(int score){
        connection = connectDB.getConnect();
        String sql = "INSERT INTO score(score) VALUE (?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1,score);
            preparedStatement.executeUpdate();
            System.out.println("Them thanh cong !");

        }catch(SQLException e) {
            e.printStackTrace();

        }finally {
            try{
                connection.close();
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }

    public int highestScore(){
        int high = 0;
        connection = connectDB.getConnect();
        String sql = "SELECT score FROM score ORDER BY score DESC LIMIT 1";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                high = resultSet.getInt("score");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return high;
    }

    public void deleteExceptTop() {
        connection = connectDB.getConnect();
        String sql = "CREATE TEMPORARY TABLE temp_table SELECT DISTINCT score FROM score ORDER BY score DESC LIMIT 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement((sql));
            preparedStatement.executeUpdate();

            sql = "DELETE FROM score WHERE score NOT IN (SELECT score FROM temp_table)";
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

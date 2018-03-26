package web.app.home_budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HomeBudgetDao {
    private static final String URL = "jdbc:mysql://localhost:3306/home_budget_web_app?characterEncoding=utf8&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection connection;

    @Autowired
    public HomeBudgetDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("No driver found");
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
        }
    }

    public void save(HomeBudget homeBudget) {
        final String sql = "INSERT INTO home_budget(type, description, amount, date) values(?, ?, ?, ?)";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, homeBudget.getType());
            prepStmt.setString(2, homeBudget.getDescription());
            prepStmt.setDouble(3, homeBudget.getAmount());
            prepStmt.setDate(4, homeBudget.getDate());
            prepStmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not add record");
            e.printStackTrace();
        }
    }

    public List<HomeBudget> read(String type) {
        List<HomeBudget> homeBudgets = new ArrayList<>();
        final String sql = "SELECT * FROM home_budget WHERE type = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, type);
            ResultSet result = prepStmt.executeQuery();
            while (result.next()) {
                HomeBudget homeBudget = new HomeBudget();
                homeBudget.setId(result.getInt("id"));
                homeBudget.setType(result.getString("type"));
                homeBudget.setDescription(result.getString("description"));
                homeBudget.setAmount(result.getDouble("amount"));
                homeBudget.setDate(result.getDate("date"));
                homeBudgets.add(homeBudget);
                //return homeBudget;
                //System.out.println(homeBudget);
            }
            return homeBudgets;
        }catch (SQLException e) {
            System.out.println("Could not get book");
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

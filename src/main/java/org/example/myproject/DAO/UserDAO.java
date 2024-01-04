package org.example.myproject.DAO;

import org.example.myproject.Model.User;

import java.sql.*;

public class UserDAO {
    public static boolean login(User user) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean signup(User user) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            //check whether username is used
            String getUsernameSql = "SELECT username FROM user WHERE username = ?";
            PreparedStatement preparedUsernameStatement = connection.prepareCall(getUsernameSql);
            preparedUsernameStatement.setString(1, user.getUsername());
            preparedUsernameStatement.execute();
            ResultSet usernameSet = preparedUsernameStatement.getResultSet();
            if (usernameSet.next()) {
                return false;
            }

            //insert new account info into db
            String storedProcedureCall = "{call InsertUser(?, ?)}";
            PreparedStatement preparedStatement = connection.prepareCall(storedProcedureCall);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String changePassword(User user) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String storedProcedureCall = "{call ChangeUserPassword(?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getPassword());
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.executeQuery();
            return callableStatement.getString(3);
        } catch (Exception e) {
            e.printStackTrace();
            return "Password change failed.";
        }
    }

    public static int getUserID(User user) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String storedProcedureCall = "{call getUserID(?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);
            callableStatement.setString(1, user.getUsername());
            ResultSet resultSet = callableStatement.executeQuery();
            return Integer.parseInt(resultSet.getString(0));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}


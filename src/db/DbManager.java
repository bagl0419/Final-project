package db;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DbManager {

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/JavaEE?currentSchema=final",
                    "postgres",
                    "postgres"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createUser(User user, String rePassword) {
        User currentUser = findByUsername(user.getUsername());
        if (currentUser != null) {
            return "errorUsername";
        }

        if (!Objects.equals(user.getPassword(), rePassword)) {
            return "errorPasswords";
        }

        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO USERS(first_name, last_name, birth_country, age, username, password) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getBirthCountry());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static User findByUsername(String username) {
        User user = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM USERS " +
                            "WHERE username = ?"
            );
            statement.setString(1, username);
            var result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setUsername(username);
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setBirthCountry(result.getString("birth_country"));
                user.setAge(result.getInt("age"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User auth(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            return null;
        }

        if (!Objects.equals(user.getPassword(), password)) {
            return null;
        }

        return user;
    }
}
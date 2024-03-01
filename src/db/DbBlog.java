package db;

import model.Blog;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbBlog {
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

    public static List<Blog> getBlogs() {
        var blogs = new ArrayList<Blog>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT b.id, b.title, b.description, b.author_id, b.created_at, b.updated_at, " +
                            "u.first_name, u.last_name, u.birth_country, u.age, u.username, u.password FROM BLOGS b " +
                            "INNER JOIN USERS u ON b.author_id = u.id " +
                            "ORDER BY b.created_at DESC"
            );
            var result = statement.executeQuery();
            while (result.next()) {
                Blog blog = new Blog();
                blog.setId(result.getLong("id"));
                blog.setTitle(result.getString("title"));
                blog.setDescription(result.getString("description"));
                blog.setCreatedAt(result.getObject("created_at", LocalDateTime.class));
                blog.setUpdatedAt(result.getObject("updated_at", LocalDateTime.class));

                User user = new User();
                user.setId(result.getLong("author_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setBirthCountry(result.getString("birth_country"));
                user.setAge(result.getInt("age"));

                blog.setAuthor(user);
                blogs.add(blog);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public static void createBlog(Blog blog) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO BLOGS(title, description, author_id, created_at, updated_at) " +
                            "VALUES(?, ?, ?, now(), null)"
            );
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getDescription());
            statement.setLong(3, blog.getAuthor().getId());
//            statement.setObject(4, LocalDateTime.now());  альтернатива now() в sql
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Blog getBlogById(Long id) {
        Blog blog = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT b.id, b.title, b.description, b.author_id, b.created_at, b.updated_at, " +
                            "u.first_name, u.last_name, u.birth_country, u.age, u.username, u.password FROM BLOGS b " +
                            "INNER JOIN USERS u ON b.author_id = u.id " +
                            "WHERE b.id = ?"
            );
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                blog = new Blog();
                blog.setId(result.getLong("id"));
                blog.setTitle(result.getString("title"));
                blog.setDescription(result.getString("description"));
                blog.setCreatedAt(result.getObject("created_at", LocalDateTime.class));
                blog.setUpdatedAt(result.getObject("updated_at", LocalDateTime.class));

                User user = new User();
                user.setId(result.getLong("author_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setBirthCountry(result.getString("birth_country"));
                user.setAge(result.getInt("age"));

                blog.setAuthor(user);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blog;
    }
}

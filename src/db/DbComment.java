package db;

import model.Blog;
import model.Comment;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbComment {
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

    public static List<Comment> getCommentsByBlogId(Long blogId) {
        List<Comment> comments = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT c.*, b.title, b.description, b.created_at AS blogCreatedAt, u.username, u.age, u.first_name, u.last_name " +
                            "FROM COMMENTS c " +
                            "INNER JOIN BLOGS b on b.id = c.blog_id " +
                            "INNER JOIN USERS u on u.id = c.author_id " +
                            "WHERE c.blog_id = ?"
            );
            statement.setLong(1, blogId);
            var result = statement.executeQuery();
            while (result.next()) {
                Comment comment = new Comment();
                comment.setId(result.getLong("id"));
                comment.setValue(result.getString("value"));
                comment.setCreatedAt(result.getObject("created_at", LocalDateTime.class));

                User author = new User();
                author.setId(result.getLong("author_id"));
                author.setUsername(result.getString("username"));
                author.setFirstName(result.getString("first_name"));
                author.setLastName(result.getString("last_name"));
                author.setAge(result.getInt("age"));

                comment.setAuthor(author);

                Blog blog = new Blog();
                blog.setId(result.getLong("blog_id"));
                blog.setTitle(result.getString("title"));
                blog.setDescription(result.getString("description"));
                blog.setCreatedAt(result.getObject("blogCreatedAt", LocalDateTime.class));

                comment.setBlog(blog);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(String value, Long authorId, Long blogId) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO COMMENTS (value, blog_id, author_id, created_at) " +
                            "VALUES (?, ?, ?, now())"
            );
            statement.setString(1, value);
            statement.setLong(2, blogId);
            statement.setLong(3, authorId);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseReader {
    private final static String URL ="jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "dz-13";
    private final static String USER_PASSWORD = "dz-13";

    public static void createDataBase() {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

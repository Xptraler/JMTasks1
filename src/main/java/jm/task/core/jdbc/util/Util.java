package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Util util;

    public static Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    private final String URL = "jdbc:mysql://localhost:3306/testdb" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "Фке10622";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

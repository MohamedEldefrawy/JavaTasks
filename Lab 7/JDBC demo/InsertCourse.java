import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCourse {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public String getQuery() {
        return query;
    }

    private String query;



    public InsertCourse() {
        connection = new DbConnection().getConnection();
        query = "insert into courses values (?,?,?)";

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void saveChanges() throws SQLException {
        connection.commit();
    }
}

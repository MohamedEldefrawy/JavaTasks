import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryStringExecute {

    private Connection connection;
    private Statement statement;
    private final String query = "select * from courses;";
    private DbConnection dbConnection;

    public QueryStringExecute() {
        dbConnection = new DbConnection();
        try {
            connection = dbConnection.getConnection();
            this.statement = dbConnection.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet selectAllStudents() throws SQLException {
        return statement.executeQuery(query);
    }

    public void closeConnection() throws SQLException {

        connection.close();

    }
}


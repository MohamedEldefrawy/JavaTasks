import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryStringExecute {

    DbConnection dbConnection = new DbConnection();

    private Connection connection;
    private Statement statement;
    private String query = "select * from students;";

    public QueryStringExecute() {
        try {
            connection = dbConnection.getCon();
            this.statement = dbConnection.getCon().createStatement();
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
}


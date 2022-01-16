import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllMain {

    public static void main(String[] args) {

        QueryStringExecute queryStringExecute = new QueryStringExecute();
        try {
            ResultSet resultSet = queryStringExecute.selectAllStudents();

            while (resultSet.next()) {
                for (int i = 1; i < 4; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                queryStringExecute.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

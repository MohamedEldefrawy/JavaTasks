import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntoMain {

    public static void main(String[] args) {
        InsertCourse insertStudent = new InsertCourse();
        PreparedStatement preparedStatement = insertStudent.getPreparedStatement();

        try {
            preparedStatement.setInt(1, 7);
            preparedStatement.setString(2, "avaJ");
            preparedStatement.setInt(3, 4);
            preparedStatement.addBatch();

            int count[] = preparedStatement.executeBatch();
            insertStudent.saveChanges();

            System.out.println(count.length + " rows changed");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                insertStudent.closeConnection();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

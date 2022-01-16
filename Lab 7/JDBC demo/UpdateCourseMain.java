import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCourseMain {
    public static void main(String[] args) {
        UpdateCourse updateCourse = new UpdateCourse();
        PreparedStatement preparedStatement = updateCourse.getPreparedStatement();

        try {
            preparedStatement.setString(1, "Dafro");
            preparedStatement.setInt(2, 6);
            preparedStatement.addBatch();
            int count[] = preparedStatement.executeBatch();

            System.out.println(count.length + " rows changed");
            updateCourse.saveChanges();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                updateCourse.closeConnection();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

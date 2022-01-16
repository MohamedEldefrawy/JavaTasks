import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCourseMain {
    public static void main(String[] args) {
        DeleteCourse deleteCourse = new DeleteCourse();
        PreparedStatement preparedStatement = deleteCourse.getPreparedStatement();

        try {
            preparedStatement.setInt(1, 7);
            preparedStatement.addBatch();
            int count[] = preparedStatement.executeBatch();
            System.out.println(count.length + " rows changed");
            deleteCourse.saveChanges();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                deleteCourse.closeConnection();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}

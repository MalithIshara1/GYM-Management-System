import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectDb(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gym","root","1234");
            return connection;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

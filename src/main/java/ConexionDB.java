import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!
    static Connection conn = null;

    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/gestion";
        String username = "root";
        String password = "22_michu";
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create a connection
            Connection connection = DriverManager.getConnection(url, username, password);
            // Perform database operations
            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }









               /* // The newInstance() call is a work around for some
                // broken Java implementations
                conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                "user=root&password=22michu");

                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();



            }  catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }*/


    }  // end main

}  // end class

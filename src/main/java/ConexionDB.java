import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB implements AutoCloseable {

    // Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!
    private Connection conn;

    public ConexionDB() {
        String url = "jdbc:mysql://localhost:3306/gestion";
        String username = "root";
        String password = "22michu";
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create a connection
            conn = DriverManager.getConnection(url, username, password);
            // Perform database operations
            // Close the connection
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    // Method to get the connection
    public Connection getConnection() {
        return conn;
    }

    // Override the close method to close the connection
    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

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
        // public static void main (String[]args){ }  // end main
    // end class
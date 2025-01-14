package mg.s5s3.db;

/**
 *
 * @author Jos
 */
import java.sql.*;
public class Database{
	private static String username="postgres";
	private static String password="postgres"; // a changer
	private static String url="jdbc:postgresql://localhost:5432/S5S3";

	public static Connection getConnection() throws Exception {
		Connection c=null;
		Class.forName("org.postgresql.Driver");
		c=DriverManager.getConnection(url,username,password);
                c.setAutoCommit(false);
		return c;
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CorporateWelfareEmployee {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
//		System.out.println("Sample Test for Commit");
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SUBHAM", "132512");
			if(conn != null) {
				System.out.println("prints the connection has established successfully");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM AdminLogin");
				System.out.println(" Username\t\tPassword");
				while(rs.next()) {
					System.out.print(" " +rs.getString(1));
					System.out.print("\t\t\t"+rs.getString(2));
				}
			}
			else {
				System.out.println("NotConnected");
			}
		}catch (ClassNotFoundException e) {
			System.out.println("Driver Class Not Found ! Exeception Occured!");
		}catch (SQLException se) {
			System.out.println("Excetion occured while Creating a connection!");
			System.out.print(se);
		}finally {
			try {
				conn.close();
			}
			catch (SQLException sqlE) {
				System.out.println("Error Occured while Closing Conection!!");
			}
		}
	}
}


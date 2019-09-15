import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CorporateWelfareEmployee {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
//		System.out.println("Sample Test for Commit");
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SUBHAM", "132512");
			if(conn != null) {
//				System.out.println("connection has established successfully");
				System.out.print("Enter your choice: \n1. Admin Login\n2. Employee Login\n");
				Scanner sc = new Scanner(System.in);
				int Choice = sc.nextInt();
				switch (Choice){
				case 1:
					System.out.print("Welcome Admin\nEnter your Login Deatils \nUsername\n");
					Scanner sc1 = new Scanner(System.in);
					String AdminUsername =  sc1.nextLine();
					System.out.print("Password\n");
					String AdminPassword = sc1.nextLine();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM AdminLogin where Username = '"+AdminUsername+"' and pass_word ='"+AdminPassword+"'");
					System.out.print("Username\t\tPassword\n");
					while(rs.next()) {
						System.out.print("" +rs.getString(1));
						System.out.print("\t\t\t"+rs.getString(2));
					}
					sc1.close();
					break;
				case 2:
					System.out.print("1. Login\n2. Register\n");
					Scanner sc2 = new Scanner(System.in);
					int EmployeeChoice = sc2.nextInt();
					switch (EmployeeChoice) {
					case 1:
						System.out.print("Welcome Employee\nEnter your Login Deatils \nUsername\n");
						Scanner sc3 = new Scanner(System.in);
						String EmployeeUsername =  sc3.nextLine();
						System.out.print("Password\n");
						String EmployeePassword = sc3.nextLine();
						Statement stmt1 = conn.createStatement();
						ResultSet rs1 = stmt1.executeQuery("SELECT * FROM EmployeeLogin where Username = '"+EmployeeUsername+"' and pass_word ='"+EmployeePassword+"'");
						System.out.print("Employee Details are:");
						while(rs1.next()) {
							System.out.print("\nID: "+rs1.getString(1));
							System.out.print("\nName: "+rs1.getString(2));
							System.out.print("\nEmail ID: "+rs1.getString(3));
							System.out.print("\nPhone Number: "+rs1.getString(4));
						}
						sc3.close();
						break;
					case 2:
						System.out.print("Registration Form:\nEnter Your Details:\nID:\n");
						Scanner sc4 = new Scanner(System.in);
						int empid = sc4.nextInt();
						sc4.nextLine();
						System.out.print("Username:");
						String employeeRegisterUsername = sc4.nextLine();
						System.out.print("Phone Number");
						long phone = sc4.nextLong();
						sc4.nextLine();
						System.out.print("New Password:");
						String newpassword = sc4.nextLine();
						System.out.print("Confirm Password:");
						String confirmpassword = sc4.nextLine();
						System.out.print("Email:");
						String email = sc4.nextLine();
						String regex = "^(.+)@(.+)$";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(email);
						if((matcher.matches() == true) && confirmpassword.equals(newpassword)) {
							Statement stmt2 = conn.createStatement();
							stmt2.executeUpdate("INSERT INTO EmployeeLogin Values("+empid+",'"+employeeRegisterUsername+"','"+email+"',"+phone+",'"+newpassword+"')");
							System.out.print("\n Registration Successfull");
						}else {
							System.out.print("Enter correct values");
						}
						sc4.close();
						break;
					}
					break;
				}
			sc.close();
			}
			else {
				System.out.println("NotConnected");
			}
		}catch (ClassNotFoundException e) {
			System.out.println("Driver Class Not Found ! Exeception Occured!");
		}catch (SQLException se) {
			System.out.println("Excetion Error occured while Creating a connection!");
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


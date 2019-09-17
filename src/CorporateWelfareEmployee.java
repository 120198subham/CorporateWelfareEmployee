import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import java.util.regex.Matcher;

public class CorporateWelfareEmployee {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, MessagingException {
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
					if(rs.next() == false) {
						System.out.print("Ivalid Username or Password");
					}else {
						System.out.print("Enter your Choice 1. See Employee Details\n2. Delete Employee Detais\n3. Logout");
						Scanner sc11 = new Scanner(System.in);
						int AdminChoice = sc11.nextInt();
						switch (AdminChoice) {
						case 1:
							System.out.print("Enter Employee ID To View Their Details");
							Scanner sc12 = new Scanner(System.in);
							int AdminEmployeeId = sc12.nextInt();
							Statement stmt5 = conn.createStatement();
							ResultSet rs2 = stmt5.executeQuery("SELECT * FROM EmployeeLogin where id = "+AdminEmployeeId+"");
							while(rs2.next()){
								System.out.print("\nID : " +rs2.getString(1));
								System.out.print("\nUsername : "+rs2.getString(2));
								System.out.print("\nEmail Address : "+rs2.getString(3));
								System.out.print("\nContact Number : " +rs2.getString(4));
								break;
							}
							ResultSet rs3 = stmt5.executeQuery("Select * from EmployeeDeatils where id = "+AdminEmployeeId+"");
							while(rs3.next()){
								System.out.print("\nWork Experence : "+rs3.getString(2));
								System.out.print("\nDate Of Birth : "+rs3.getString(3));
								System.out.print("\nMatrimonial Status : " +rs3.getString(4));
								System.out.print("\nNative Place : "+rs3.getString(5));
								System.out.print("\nFull Address : "+rs3.getString(6));
							}
							sc12.close();
						case 2:
							System.out.print("Enter Employee ID To View Their Details");
							Scanner sc13 = new Scanner(System.in);
							int AdminEmployeeDproId = sc13.nextInt();
							Statement stmt6 = conn.createStatement();
							stmt6.executeUpdate("DELETE FROM EmploeeDeatils,EmployeeLogin where id = "+AdminEmployeeDproId+"");
							System.out.print("Removed Record of The Employee");
							sc13.close();
						case 3:
							System.out.print("Logged Out Succesfully");
							break;
						default :
							System.out.print("Enter Correct Option");
						}
						sc11.close();
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
						int count = 0;
						while(rs1.next()) {
							count = Integer.parseInt(rs1.getString(6));
						}
						if(count == 1) {
							System.out.print("Welcome to CorporateWelfareEmployee project as you are first time loging in please fill the following details:\n");
							Scanner sc10 = new Scanner(System.in);
							System.out.print("Enter Your ID Number:\n");
							int IDfirstLogin = sc10.nextInt();
							sc10.nextLine();
							System.out.print("Enter Your Work Experence till date(IF FRESHER type 0):\n");
							int WorkExperence = sc10.nextInt();
							sc10.nextLine();
							System.out.print("Enter your Date of Birth(DD-MM-YYYY):");
							String DOB = sc10.nextLine();
							System.out.print("Matrimonial Status:\n");
							String Matrimonial = sc10.nextLine();
							System.out.print("Native Location/Place:\n");
							String Native = sc10.nextLine();
							System.out.print("Full Home Address\n");
							String Address = sc10.nextLine();
							stmt1.executeUpdate("INSERT INTO EmployeeDeatils  Values("+IDfirstLogin+","+WorkExperence+",'"+DOB+"','"+Matrimonial+"','"+Native+"','"+Address+"')");
							stmt1.execute("UPDATE EmployeeLogin SET count = 0 where id = "+IDfirstLogin+"");
							System.out.print("\nDetails Updated Successfull");
							sc10.close();
						}else {
							System.out.print("Select an Option:\n1. See Your Details\n2. Update you Deatils\n3. Logout");
							Scanner sc5 = new Scanner(System.in);
							int NormalLogin = sc5.nextInt();
							switch (NormalLogin) {
							case 1:
								ResultSet rs2 = stmt1.executeQuery("SELECT * FROM EmployeeLogin where Username = '"+EmployeeUsername+"' and pass_word ='"+EmployeePassword+"'");
								while(rs2.next()) {
									System.out.print("ID:"+rs2.getString(1));
									System.out.print("\nUsername:"+rs2.getString(2));
									System.out.print("\nEmail:"+rs2.getString(3));
									System.out.print("\nContact Number:"+rs2.getString(4));
								}
								sc5.close();
								break;
							case 2:
								System.out.print("Select an Option:\n1. Update Password\n2. Update Email");
								Scanner sc6 = new Scanner(System.in);
								int UpdateEmployeeOption = sc6.nextInt();
								switch (UpdateEmployeeOption) {
								case 1:
									ResultSet rs3 = stmt1.executeQuery("SELECT * FROM EmployeeLogin where Username = '"+EmployeeUsername+"' and pass_word ='"+EmployeePassword+"'");
									String PreviousPassword = "";
									String ID1 ="";
									while(rs3.next()) {
										ID1 = rs3.getString(1);
										PreviousPassword =  rs3.getString(5);
									}
									Scanner sc7 = new Scanner(System.in);
									System.out.print("Enter Current Password:");
									String CurrentPassword = sc7.nextLine();
									if(CurrentPassword.equals(PreviousPassword)) {
										System.out.print("New Password:");
										Scanner sc8 = new Scanner(System.in);
										String NewPassword = sc8.nextLine();
										System.out.print("Confirm new Password");
										String ConfirmNewPassword = sc8.nextLine();
										if(NewPassword.equals(ConfirmNewPassword)) {
											Statement stmt3 = conn.createStatement();
											stmt3.execute("UPDATE EmployeeLogin SET pass_word = '"+NewPassword+"' where id = "+ID1+"");
											System.out.print("Password Changed");
										}else {
											System.out.print("Password Not Matched");
										}
										sc8.close();
									}
									sc7.close();
									break;
								case 2:
									ResultSet rs4 = stmt1.executeQuery("SELECT * FROM EmployeeLogin where Username = '"+EmployeeUsername+"' and pass_word ='"+EmployeePassword+"'");
									String ID2 ="";
									while(rs4.next()) {
										ID2 = rs4.getString(1);
									}
									Scanner sc9 = new Scanner(System.in);
									System.out.print("Enter new Email");
									String NewEmail = sc9.nextLine();
									String OTP = String.valueOf(generateOTP.generateOTP(4));
									JavaMail.sentmail(NewEmail,OTP);
									System.out.print("Enter OTP sent to your mail for Verification\n");
									String otp = sc9.nextLine();
									if(OTP.equals(otp)) {
										Statement stmt4 = conn.createStatement();
										stmt4.execute("UPDATE EmployeeLogin SET email = '"+NewEmail+"' where id = "+ID2+"");
										System.out.print("\nUpdate Successfull");
									}
									sc9.close();
									break;
								default:
									System.out.print("Enter Proper Option");
								}
							case 3:
								System.out.print("Logout Succesfull");
								break;
							default :
								System.out.print("Enter Proper Option");
							}
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
							String OTP = String.valueOf(generateOTP.generateOTP(4));
							JavaMail.sentmail(email,OTP);
							System.out.print("Enter OTP sent to your mail for Registration\n");
							Scanner sc5 = new Scanner(System.in);
							String otp = sc5.nextLine();
							if(OTP.equals(otp)) {
								Statement stmt2 = conn.createStatement();
								stmt2.executeUpdate("INSERT INTO EmployeeLogin Values("+empid+",'"+employeeRegisterUsername+"','"+email+"',"+phone+",'"+newpassword+"',1)");
								System.out.print("\n Registration Successfull");
							}
							sc5.close();
						}else {
							System.out.print("Enter correct values");
						}
						sc4.close();
						break;
					default :
						System.out.print("Enter Proper Option");
					}
					break;
				default :
					System.out.print("Enter Proper Option");
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

import java.sql.*;
import java.util.*;

class SQL
{
	// For Database Connection
	protected Statement s;
	protected Connection con;

	// For Member
	public Member member = new Member();
	private boolean x;

	// For Handphone
	private ArrayList hpList;
	// private Handphone temp = new Handphone();

	// Get Connection to database
	public SQL()
	{
		getConnection();
		hpList = new ArrayList();
		x = false;
	}

	public Connection getConnection()
	{

		try
		{
			Class.forName
			("sun.jdbc.odbc.JdbcOdbcDriver");

		}
		catch (java.lang.ClassNotFoundException e)
		{
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try
		{
			con = DriverManager.getConnection("jdbc:odbc:Handphone");
			System.out.println("The connection is successful");

		}
		catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}

		return con;
	}

	//******************************************************************** register a new member *************************************************************//
	public void setNewMember(Member mem)
	{
		try
		{
			/*
			String sql = "INSERT INTO MEMBER "
						+ "VALUES (?,?,?,?,?,?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql);
			//below statement is wrong [Microsoft][ODBC Microsoft Access Driver]Optional feature not implemented
			ps.setLong(1, mem.getID());
			ps.setString(2, mem.getPassword());
			ps.setString(3, mem.getLName());
			ps.setString(4, mem.getFName());
			ps.setLong(5, mem.getBonus());
			ps.setString(6, mem.getContact());
			ps.setString(7, mem.getEmail());
			ps.setString(8, mem.getIC());
			
			ps.executeUpdate();
			ps.close();
			*/
			
			String sql = "INSERT INTO MEMBER (MemberID, Password, LastName, "
						+ "FirstName, BonusPoint, ContactNo, Email, IC) "
						+ "VALUES(" + mem.getID() + ",'" + mem.getPassword() + "','" + mem.getLName() + "','" + mem.getFName() + "'," + mem.getBonus() + ",'" + mem.getContact() + "','" + mem.getEmail() + "','"+mem.getIC()+"')";


			s = con.createStatement();

			s.executeUpdate(sql);
			System.out.println("You have been add new record into MEMBER table");
			s.close();
			con.close();
			con.commit();
			

		}
		catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		
	}

	public long getID()
	{
		long id = 0;
		try
		{
			String sql = "SELECT MemberID FROM MEMBER ORDER BY MemberID DESC";

			s = con.createStatement();

			ResultSet rs = s.executeQuery(sql);
			rs.next();

			id = rs.getLong("MemberID");

		}
		catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		System.out.println("ID " + (id + 1) + " have been given");
		return (id + 1);
		
	}

	//************************************************************************** User Login **********************************************************//
	public boolean Login(long inputID, String inputPassword)
	{
		member.setID(inputID);
		member.setPassword(inputPassword);


		try
		{
			String sql = "SELECT MemberID " +
						"FROM MEMBER WHERE MemberID = " + member.getID() + " AND Password = '" + member.getPassword() + "'";
			System.out.println(sql);
			s = con.createStatement();

			ResultSet rs = s.executeQuery(sql);
			rs.next(); 

			member.setID(rs.getLong("MemberID"));

			if (member.getID() != 0)
				x = true;
		}

		catch (SQLException ex)
		{

			System.err.println("SQLException: " + ex.getMessage());
		}
		

		return x;
	}

	//************************************************************************ Member Profile ***********************************************************//
	public Member getMember()
	{

		try
		{
			String sql = "SELECT MemberID, LastName, FirstName, BonusPoint, ContactNo, Email, IC, Password " +
						"FROM MEMBER WHERE MemberID = " + member.getID();

			s = con.createStatement();

			ResultSet rs = s.executeQuery(sql);
			rs.next(); // [PF] must have next then only can work
			member.setLName(rs.getString("LastName"));
			member.setFName(rs.getString("FirstName"));
			member.setBonus(rs.getLong("BonusPoint"));
			member.setContact(rs.getString("ContactNo"));
			member.setEmail(rs.getString("Email"));
			member.setIC(rs.getString("IC"));
			member.setPassword(rs.getString("Password"));
			//s.close();
			//con.close();

		}
		catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		
		return member;

	}

	//*********************************************************************** Update Member Bonus Point ***********************************************//
	public void SetBonus(long id, long bonus)
	{
		try
		{
			String sql = "UPDATE MEMBER SET BonusPoint = ("+bonus+"+BonusPoint) WHERE MemberID="+id;

			// Create a Prepared statement
			/*PreparedStatement ps = con.prepareStatement(sql);

			ps.setLong(1, bonus);

			ps.executeUpdate();
			*/
			s = con.createStatement();

			s.executeUpdate(sql);
			con.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

	//********************************************************************** Update Password ***************************************************//
	public String updatePassword(long id, String pw)
	{
		try
		{
			String sql = "UPDATE MEMBER SET Password='"+pw+"' WHERE MemberID=" + id;

			s = con.createStatement();

			s.executeUpdate(sql);
			con.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return pw;

	}

	//************************************************************************* Search Handphone ****************************************************//

	public ArrayList getPhone(Handphone hp)
	{

		try
		{
			// [PF] search by price
			String str_price = "";
			if ( hp.getPrice() > 0 ){
				System.out.println("We have a price here");
				str_price = "AND Price = " + hp.getPrice();
			}
			// [PF] end
			String sql = "SELECT * FROM Handphone WHERE brand LIKE '%" + hp.getBrand() + "%' AND Model LIKE '%" + hp.getModel() + "%'" +
						"AND OS_system LIKE '%" + hp.getOS() + "%' AND Camera LIKE '%" + hp.getCamera() + "%' AND Type LIKE '%" + hp.getType() + "%' AND Memory LIKE '%" +
						"" + hp.getMemory() + "%' AND Card_Slot LIKE '%" + hp.getCardSlot() + "%' " + str_price; // [PF] AND Price = " + hp.getPrice() ;
			System.out.println(sql);
			s = con.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next())
			{
				Handphone temp = new Handphone();
				temp.setBrand(rs.getString("Brand"));
				temp.setModel(rs.getString("Model"));
				temp.setOS(rs.getString("OS_system"));
				temp.setCamera(rs.getString("Camera"));
				temp.setType(rs.getString("Type"));
				temp.setMemory(rs.getString("Memory"));
				temp.setCardSlot(rs.getString("Card_Slot"));
				temp.setPrice(rs.getDouble("Price"));

				hpList.add(temp);
				

			}

		}
		catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		
		return hpList;

	}

	protected void finalize() throws Throwable
	{
		if (con != null)
			con.close();
	}

}
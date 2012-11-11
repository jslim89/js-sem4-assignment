class Member extends Customer
{
	private long ID;
	private String pw;
	private long bonus;
	private String email;

	public Member()
	{
		super();
		ID = 0;
		pw = "";
		bonus = 0;
		email = "";
	}

	public Member(String FN, String LN, String IC, String C, long ID, String P, long B, String E)
	{
		super(FN, LN, IC, C);
		this.ID = ID;
		pw = P;
		bonus = B;
		email = E;
	}

	public void setID(long I)
	{ ID = I; }

	public void setPassword(String P)
	{ pw = P; }

	public void setBonus(long B)
	{ bonus = B; }

	public void setEmail(String E)
	{ email = E; }

	public long getID()
	{ return ID; }

	public String getPassword()
	{ return pw; }

	public long getBonus()
	{ return bonus; }

	public String getEmail()
	{ return email; }

	public String toString()
	{
		return "ID: " + getID() + "\n" +
			// Polymorphysm
				super.toString() +
				"Bonus Point: " + getBonus() + "\n" +
				"E-Mail: " + getEmail() + "\n";
	}
}
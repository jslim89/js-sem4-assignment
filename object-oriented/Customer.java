class Customer
{
	protected String Fname;
	protected String Lname;
	protected String IC;
	protected String contact;

	public Customer()
	{
		Fname = "";
		Lname = "";
		IC = "";
		contact = "";
	}

	public Customer(String Fname,String Lname, String IC,String contact)
	{
		this.Fname = Fname;
		this.Lname = Lname;
		this.IC = IC;
		this.contact = contact;
	}

	public void setFName(String Fname)
	{ this.Fname = Fname; }

	public void setLName(String Lname)
	{ this.Lname = Lname; }

	public void setIC(String IC)
	{ this.IC = IC; }

	public void setContact(String contact)
	{ this.contact = contact; }

	public String getFName()
	{ return Fname; }

	public String getLName()
	{ return Lname; }

	public String getIC()
	{ return IC; }

	public String getContact()
	{ return contact; }

	public String toString()
	{
		return "First Name: " + getFName() + "\n" +
				"Last Name: " + getLName() + "\n" +
				"IC No.: " + getIC() + "\n" +
				"Contact: " + getContact() + "\n";
	}
}
class Handphone
{
	private String model;
	private String os;
	private String cam;
	private String type;
	private String memory;
	private String card;
	private Double price;
	private String brand;

	public Handphone()
	{
		model = "";
		os = "";
		cam = "";
		type = "";
		memory = "";
		card = "";
		price = 0.0;
		brand = "";
	}

	public Handphone(String M, String O, String C, String T, String memory, String card, double P)
	{
		model = M;
		os = O;
		cam = C;
		type = T;
		this.memory = memory;
		this.card = card;
		price = P;
	}

	public Handphone(String M, String O, String C, String T, String memory, String card, double P, String B)
	{
		model = M;
		os = O;
		cam = C;
		type = T;
		this.memory = memory;
		this.card = card;
		price = P;
		brand = B;
	}

	public void setModel(String M)
	{ model = M; }

	public void setOS(String O)
	{ os = O; }

	public void setCamera(String C)
	{ cam = C; }

	public void setType(String T)
	{ type = T; }

	public void setMemory(String ME)
	{ memory = ME; }

	public void setCardSlot(String CS)
	{ card = CS; }

	public void setPrice(double P)
	{ price = P; }

	public String getModel()
	{ return model; }

	public String getOS()
	{ return os; }

	public String getCamera()
	{ return cam; }

	public String getType()
	{ return type; }

	public String getMemory()
	{ return memory; }

	public String getCardSlot()
	{ return card; }

	public double getPrice()
	{ return price; }

	public void setBrand(String B)
	{ brand = B; }

	public String getBrand()
	{ return brand; }

	public String toString()
	{
		return "Model: " + getModel() + "\n" +
			   "OS System: " + getOS() + "\n" +
			   "Camera: " + getCamera() + "\n" +
			   "Type: " + getType() + "\n" +
			   "Memory: " + getMemory() + "\n" +
			   "Card Slot: " + getCardSlot() + "\n" +
			   "Price: " + getPrice() + "\n";
	}
}
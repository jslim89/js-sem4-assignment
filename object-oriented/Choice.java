import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Choice extends JFrame implements ActionListener
{
	private ArrayList hpList;

	private JButton search = new JButton("Search");
	private JButton close = new JButton("Close");
   	private JButton jbnForward = new JButton(">>");
   	private JButton jbnBack    = new JButton("<<");
	private JButton addBill = new JButton("Add to Bill");
	private JButton printBill = new JButton("Print Invoice");
	private JTextField jtfModel = new JTextField("");
	private JTextField jtfOs = new JTextField("");
	private JTextField jtfCam = new JTextField("");
	private JTextField jtfType = new JTextField("");
	private JTextField jtfMemory = new JTextField("");
	private JTextField jtfCard = new JTextField("");
	private JTextField jtfPrice = new JTextField("");
	private JTextField jtfBrand= new JTextField("");
	private JButton jbtClear = new JButton("Clear");
	private Handphone hp;
	private Handphone temp = new Handphone();
	private SQL link = new SQL();
	private Member mem = new Member();
	private String searchBrand;
	private String invoice;
	private int count=0;
	private double total = 0.0;
	private int recordNumber;
	private long memID;
	private String tempN;

	public Choice(long ID, String N)
	{
		link.member.setID(ID);
		mem.setID(link.member.getID());
		recordNumber = -1;

		tempN = N;
		memID = ID;
		invoice = "\nID: " + memID+"\nName: "+N+"\n";

		hpList = new ArrayList();

		Container c = getContentPane();
		c.setLayout(new BorderLayout(5,10));
	
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(8, 2));
		body.add(new JLabel("Brand: "));
		body.add(jtfBrand);
		body.add(new JLabel("Model: "));
		body.add(jtfModel);
		body.add(new JLabel("OS System: "));
		body.add(jtfOs);
		body.add(new JLabel("Camera: "));
		body.add(jtfCam);
		body.add(new JLabel("Type: "));
		body.add(jtfType);
		body.add(new JLabel("Memory: "));
		body.add(jtfMemory);
		body.add(new JLabel("Card Slot: "));
		body.add(jtfCard);
		body.add(new JLabel("Price: RM "));
		body.add(jtfPrice);


		JPanel below = new JPanel();
		below.setLayout(new GridLayout(2,1));
		JPanel first = new JPanel();
		first.setLayout(new FlowLayout(FlowLayout.CENTER));
		first.add(jbnBack);
		first.add(search);
		first.add(jbtClear);
		first.add(jbnForward);
		JPanel sec = new JPanel();
		sec.setLayout(new FlowLayout(FlowLayout.CENTER));
		sec.add(addBill);
		sec.add(printBill);
		sec.add(close);
		below.add(first);
		below.add(sec);

		c.add(new JLabel("Welcome to SMD Enterprise"), BorderLayout.NORTH);
		c.add(body,BorderLayout.CENTER);
		c.add(below,BorderLayout.SOUTH);

		search.addActionListener(this);
		jbnBack.addActionListener(this);
		jbnForward.addActionListener(this);
		addBill.addActionListener(this);
		close.addActionListener(this);
		printBill.addActionListener(this);
		jbtClear.addActionListener(this);

		setTitle("Mobile Phone");
		setLocation(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setSize(400, 300);
	}

	public void actionPerformed(ActionEvent e)
	{
		
		
		if (e.getSource() == search)
		{
			double dPrice = 0.0;
			temp.setBrand(jtfBrand.getText());
			temp.setModel(jtfModel.getText());
			temp.setOS(jtfOs.getText());
			temp.setCamera(jtfCam.getText());
			temp.setType(jtfType.getText());
			temp.setMemory(jtfMemory.getText());
			temp.setCardSlot(jtfCard.getText());
			// [PF] get hp price
			try {
				dPrice = Double.parseDouble(jtfPrice.getText());
				temp.setPrice(dPrice);
			}
			catch(NumberFormatException nfex){
				System.err.println("NumberFormatException: " + nfex.getMessage());
			}
			// [PF] end
			searchHp();
		}

		else if (e.getSource() == jbtClear)
		{
			clear();
		}

		else if (e.getSource() == jbnForward)
		{
			displayNextRecord();
		}

		else if (e.getSource() == jbnBack)
		{
			displayPreviousRecord();
		}

		else if (e.getSource() == addBill)
		{
			if (jtfBrand.getText().equals("") || jtfModel.getText().equals("") || jtfOs.getText().equals("") || jtfCam.getText().equals("") || 
				jtfType.getText().equals("") || jtfMemory.getText().equals("") || jtfCard.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Nothing selected. Please choose before press this button!");
			}
			else
			{
				count++;
				invoice = invoice + count+".    " + jtfBrand.getText() + "\t" + jtfModel.getText
() + "\nPrice: RM" + jtfPrice.getText() + "\n";
				total = total + Double.parseDouble(jtfPrice.getText());
				JOptionPane.showMessageDialog(null, jtfModel.getText() +" have been added to your bill");
			}
		}

		else if (e.getSource() == printBill)
		{
			if (count == 0)
				JOptionPane.showMessageDialog(null, "You haven select any item!");
			else
			{
				long bonus = (long)(total * 3);
				invoice = getTime()+ invoice + "Total Price: RM" + total;
				JOptionPane.showMessageDialog(null, invoice);
				link.SetBonus(memID, bonus);
				count = 0;
				invoice = "\nID: " + memID + "\nName: " + tempN + "\n";
			}
		}

		else if (e.getSource() == close)
		{
			setVisible(false);
			mem = link.getMember();
			new Profile(mem);
		}

	}

	public void searchHp()
	{
		
		hpList.clear();

		recordNumber = 0;

		if (temp.getBrand().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please choose a brand to search.");
		}
		else
		{
			// [PF] fix hp search first, then only say if empty or not
			hpList = link.getPhone(temp);
			if (hpList.size() == 0)
			{
				JOptionPane.showMessageDialog(null, "No records found.");
			}
			else
			{
				hp = (Handphone)hpList.get(recordNumber);
				// displaying search record in text fields 
				jtfBrand.setText(hp.getBrand());
				jtfModel.setText(hp.getModel());
				jtfOs.setText(hp.getOS());
				jtfCam.setText(hp.getCamera());
				jtfType.setText(hp.getType());
				jtfMemory.setText(hp.getMemory());
				jtfCard.setText(hp.getCardSlot());
				jtfPrice.setText(""+hp.getPrice());
			}
			// [PF] end
		}

	}

	public void displayNextRecord()
	{
               
		recordNumber++;

		if (recordNumber >= hpList.size())
		{
			JOptionPane.showMessageDialog(null, "You have reached end of " +
					"search results");

			/*if user has reached the end of results, disable forward button*/
			jbnForward.setEnabled(false);
			jbnBack.setEnabled(true);

			// dec by one to counter last inc
			recordNumber--;
		}
		else
		{
			jbnBack.setEnabled(true);
			hp = (Handphone)hpList.get(recordNumber);

			// displaying search record in text fields 
			jtfBrand.setText(hp.getBrand());
			jtfModel.setText(hp.getModel());
			jtfOs.setText(hp.getOS());
			jtfCam.setText(hp.getCamera());
			jtfType.setText(hp.getType());
			jtfMemory.setText(hp.getMemory());
			jtfCard.setText(hp.getCardSlot());
			jtfPrice.setText("" + hp.getPrice());
		}
	}


	public void displayPreviousRecord()
	{
           
		recordNumber--;

		if (recordNumber < 0)
		{
			JOptionPane.showMessageDialog(null, "You have reached begining " +
					"of search results");

			/*if user has reached the begining of results, disable back button*/
			jbnForward.setEnabled(true);
			jbnBack.setEnabled(false);

			// inc by one to counter last dec
			recordNumber++;
		}
		else
		{
			jbnForward.setEnabled(true);
			hp = (Handphone)hpList.get(recordNumber);

			// displaying search record in text fields 
			jtfBrand.setText(hp.getBrand());
			jtfModel.setText(hp.getModel());
			jtfOs.setText(hp.getOS());
			jtfCam.setText(hp.getCamera());
			jtfType.setText(hp.getType());
			jtfMemory.setText(hp.getMemory());
			jtfCard.setText(hp.getCardSlot());
			jtfPrice.setText("" + hp.getPrice());
		}

	}

	public void clear()
	{
			jtfBrand.setText("");
			jtfModel.setText("");
			jtfOs.setText("");
			jtfCam.setText("");
			jtfType.setText("");
			jtfMemory.setText("");
			jtfCard.setText("");
			jtfPrice.setText("");

			recordNumber = -1;
			hpList.clear();
			jbnForward.setEnabled(true);
			jbnBack.setEnabled(true);
	}

	static String getTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return "Date: " + dateFormat.format(date);
	} 
	
}
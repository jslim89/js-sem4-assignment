import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Profile extends JFrame implements ActionListener
{
	private JLabel jlbID = new JLabel();
	private JLabel jlbName = new JLabel();
	private JLabel jlbIC = new JLabel();
	private JLabel jlbContact = new JLabel();
	private JLabel jlbBonus = new JLabel();
	private JLabel jlbEmail = new JLabel();
	private JButton searchHP = new JButton("Search Mobile Phone");
	private JButton logout = new JButton("Log Out");
	private JButton changePW = new JButton("Change Password");

	private long tempID;
	private String tempName;
	private Member tempMem = new Member();

	public Profile(Member M)
	{
		tempMem = M;
		Container c = getContentPane();
		c.setLayout(new BorderLayout(5, 10));

		jlbID.setText(""+M.getID());
		tempName = M.getFName() + " " + M.getLName();
		tempID = M.getID();
		jlbName.setText(M.getFName() +" " + M.getLName());
		jlbIC.setText(M.getIC());
		jlbContact.setText(M.getContact());
		jlbBonus.setText(""+M.getBonus());
		jlbEmail.setText(M.getEmail());

		JPanel upper = new JPanel();
		upper.setLayout(new GridLayout(6, 2));
		upper.add(new JLabel("ID: "));
		upper.add(jlbID);
		upper.add(new JLabel("Name: "));
		upper.add(jlbName);
		upper.add(new JLabel("IC: "));
		upper.add(jlbIC);
		upper.add(new JLabel("Contact: "));
		upper.add(jlbContact);
		upper.add(new JLabel("Bonus Point: "));
		upper.add(jlbBonus);
		upper.add(new JLabel("E-Mail: "));
		upper.add(jlbEmail);

		JPanel below = new JPanel();
		below.setLayout(new GridLayout(2,1));
		JPanel fir = new JPanel();
		fir.setLayout(new FlowLayout(FlowLayout.CENTER));
		fir.add(searchHP);
		JPanel sec = new JPanel();
		sec.setLayout(new FlowLayout());
		sec.add(changePW);
		sec.add(logout);
		below.add(fir);
		below.add(sec);

		c.add(upper,BorderLayout.CENTER);
		c.add(below,BorderLayout.SOUTH);

		searchHP.addActionListener(this);
		logout.addActionListener(this);
		changePW.addActionListener(this);


		setTitle("Member Profile");
		setLocation(200, 200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == searchHP)
		{
			setVisible(false);
			new Choice(tempID,tempName);
		}

		else if (e.getSource() == logout)
		{
			JOptionPane.showMessageDialog(null, "You have been logout!!!");
			setVisible(false);
			new Main();
		}

		else if (e.getSource() == changePW)
		{
			setVisible(false);
			new Password(tempMem);
		}
	}


}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Main extends JFrame implements ActionListener
{
	// Main()
	private JButton close = new JButton("Close");
	private JButton signin = new JButton("Login");
	private JButton reg = new JButton("Register");
	private JButton dir = new JButton("Direct Access");
	private JTextField id = new JTextField(6);
	private JPasswordField pw = new JPasswordField(12);

	private SQL link = new SQL();

	public Main()
	{

		Container c = getContentPane();
		c.setLayout(new GridLayout(2,1));

		JPanel upper = new JPanel();

		upper.setLayout(new GridLayout(2, 2));
		upper.add(new JLabel("User ID: "));
		upper.add(id);
		upper.add(new JLabel("Password: "));
		upper.add(pw);

		JPanel below = new JPanel();
		below.setLayout(new FlowLayout());
		below.add(signin);
		below.add(reg);
		below.add(close);

		signin.addActionListener(this);
		reg.addActionListener(this);
		close.addActionListener(this);

		c.add(upper);
		c.add(below);

		setTitle("SMD Enterprise");
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		pack();
	}

	public static void main(String args[])
	{
		JOptionPane.showMessageDialog(null, "Welcome to SMD Enterprise!!!");
		new Main();
	}

	public void actionPerformed(ActionEvent e)
	{
		boolean x=true;
		String num = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if (e.getSource() == signin)
		{
			if (id.getText().equals("") || pw.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please fill in the neccessary information!");
			else
			{
				for(int i=0;i<id.getText().length();i++)
				{
					for(int j=0;j<num.length();j++)
					{
						if (id.getText().charAt(i) == num.charAt(j))
						{
							x = false;
							break;
						}
					}
					if (!x)
					{
						break;
					}
				}
				if (!x)
				{
					JOptionPane.showMessageDialog(null, "Member ID only contain digit!");
				}
				else
				{
					if (!(link.Login(Long.parseLong(id.getText()), pw.getText())))
					{
						JOptionPane.showMessageDialog(null, "Invalid User ID & Password! Please check your User ID & Password!!!");
					}
					else
					{

						Member mem = link.getMember();
						new Profile(mem);
						this.setVisible(false);
						clear();

					}
				}
			}
		}

		if (e.getSource() == reg)
		{
			this.setVisible(false);
			new Register();
		}
		if (e.getSource() == close)
		{
			JOptionPane.showMessageDialog(null, "Thanks for using this System!");
			System.exit(0);
		}
	}

	public void clear()
	{
		id.setText("");
		pw.setText("");
	}


}


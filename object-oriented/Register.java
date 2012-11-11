import java.awt.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Register extends JFrame implements ActionListener
{
	private JTextField fName = new JTextField();
	private JTextField lName = new JTextField();
	private JTextField ic = new JTextField();
	private JTextField contact = new JTextField();
	private JTextField email = new JTextField();
	private JPasswordField pw = new JPasswordField();
	private JPasswordField confirm_pw = new JPasswordField();
	private JButton signup = new JButton("Sign Up");
	private JButton cancel = new JButton("Cancel");
	private JButton reset = new JButton("Reset");
	private JButton regHelp = new JButton("Help");
	private SQL link = new SQL();

	public Register()
	{
		fName.setBackground(Color.yellow);
		lName.setBackground(Color.yellow);
		ic.setBackground(Color.yellow);
		pw.setBackground(Color.yellow);
		confirm_pw.setBackground(Color.yellow);

		Container c = getContentPane();
		c.setLayout(new BorderLayout(5,10));

		JPanel regUpper = new JPanel();
		regUpper.setLayout(new GridLayout(7, 2));
		regUpper.add(new JLabel("First Name: "));
		regUpper.add(fName);
		regUpper.add(new JLabel("Last Name: "));
		regUpper.add(lName);
		regUpper.add(new JLabel("IC No: "));
		regUpper.add(ic);
		regUpper.add(new JLabel("Contact No: "));
		regUpper.add(contact);
		regUpper.add(new JLabel("E-mail: "));
		regUpper.add(email);
		regUpper.add(new JLabel("Password: "));
		regUpper.add(pw);
		regUpper.add(new JLabel("Confirm Password: "));
		regUpper.add(confirm_pw);

		JPanel regBelow = new JPanel();
		regBelow.setLayout(new FlowLayout());
		regBelow.add(signup);
		regBelow.add(reset);
		regBelow.add(regHelp);
		regBelow.add(cancel);

		signup.addActionListener(this);
		reset.addActionListener(this);
		regHelp.addActionListener(this);
		cancel.addActionListener(this);

		c.add(regUpper,BorderLayout.CENTER);
		c.add(regBelow,BorderLayout.SOUTH);

		setTitle("Registration");
		setResizable(false);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	public void clear()
	{

		fName.setText("");
		lName.setText("");
		ic.setText("");
		pw.setText("");
		confirm_pw.setText("");
		contact.setText("");
		email.setText("");
	}

	public void actionPerformed(ActionEvent e)
	{
		char num[] = {'1','2','3','4','5','6','7','8','9','0'};
		String cha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
		boolean ver = false, icCheck = false;
		int coun = 0;
		if (e.getSource() == signup)
		{
			if (fName.getText().equals("") || lName.getText().equals("") || ic.getText().equals("") || pw.getText().equals("") || confirm_pw.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill in all the yellow field!");
			}
			else
			{
				if (!(pw.getText().equals(confirm_pw.getText())))
				{
					JOptionPane.showMessageDialog(null, "Your password is not confirmed, please re-check!");
				}
				else
				{
					if (email.getText().equals(""))
					{
						ver = true;
					}
					else
					{
						ver = false;
						for (int i = 0; i < email.getText().length(); i++)
						{
							if (email.getText().charAt(i) == '@')
							{
								coun++;								
							}
						}
						if (coun == 1)
							ver = true;
					}
					if (ic.getText().length() == 12)
					{
						icCheck = true;
					}
					if (!ver)
					{
						JOptionPane.showMessageDialog(null, "Your E-Mail format is invalid! Please re-check '@' character");
					}
					else
					{
						if (!icCheck)
						{
							JOptionPane.showMessageDialog(null, "Wrong IC format! Your IC No. should contain 12 digits!");
														
						}
						else
						{
							int mon = Integer.parseInt(ic.getText().charAt(2) + "" + ic.getText().charAt(3));
							int day = Integer.parseInt(ic.getText().charAt(4) + "" + ic.getText().charAt(5));

							if (mon > 12 || day > 32)
								JOptionPane.showMessageDialog(null, "IC No. Date of birth is invalid! Please Check!");
							else
							{
								
								for (int i = 0; i < fName.getText().length(); i++)
								{
									for (int j = 0; j < num.length; j++)
									{
										if (fName.getText().charAt(i) == num[j])
										{
											ver = false;
											break;
										}
									}
									if (!ver)
									{
										break;
									}
								}
								if (!ver)
								{
									JOptionPane.showMessageDialog(null, "The name cannot contain digit!");
								}
								else
								{

									for (int i = 0; i < lName.getText().length(); i++)
									{
										for (int j = 0; j < num.length; j++)
										{
											if (lName.getText().charAt(i) == num[j])
											{
												ver = false;
												break;
											}
										}
										if (!ver)
										{
											break;
										}
									}

									if (!ver)
									{
										JOptionPane.showMessageDialog(null, "The name cannot contain digit!");
									}

									else
									{
										for (int i = 0; i < ic.getText().length(); i++)
										{
											for (int j = 0; j < cha.length(); j++)
											{
												if (ic.getText().charAt(i) == cha.charAt(j))
												{
													ver = false;
													break;
												}
											}
											if (!ver)
											{
												break;
											}
										}
										if (!ver)
										{
											JOptionPane.showMessageDialog(null, "The IC No. format is only digits! Cannot contain other character");
										}
										else
										{
											for (int i = 0; i < contact.getText().length(); i++)
											{
												for (int j = 0; j < cha.length(); j++)
												{
													if (contact.getText().charAt(i) == cha.charAt(j))
													{
														ver = false;
														break;
													}
												}
												if (!ver)
												{
													break;
												}
											}
											if (!ver)
											{
												JOptionPane.showMessageDialog(null, "Invalid Contact No.! Cannot contain alphabet character");
											}
											else
											{
												Member newMember = new Member();
												newMember.setFName(fName.getText());
												newMember.setLName(lName.getText());
												newMember.setIC(ic.getText());
												newMember.setPassword(pw.getText());
												newMember.setContact(contact.getText());
												newMember.setEmail(email.getText());
												newMember.setID(link.getID());
												link.setNewMember(newMember);

												JOptionPane.showMessageDialog(null, "Congratulation! Your have been successful registered!\nYour Member ID is "+newMember.getID()+"!");
												setVisible(false);
												new Profile(newMember);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (e.getSource() == cancel)
		{
			new Main();
			setVisible(false);
		}
		if (e.getSource() == reset)
		{
			clear();
		}
		if (e.getSource() == regHelp)
		{
			JOptionPane.showMessageDialog(null,"The yellow field MUST fill in some information.\n"
								+"After filled, click on (Sign Up) button, then the "
								+"registration is succeed.");
		}
	}
}
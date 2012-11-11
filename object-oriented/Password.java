import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Password extends JFrame implements ActionListener
{
	private JPasswordField oldPassword = new JPasswordField();
	private JPasswordField newPassword = new JPasswordField();
	private JPasswordField confirmPassword = new JPasswordField();
	private JButton ok = new JButton("Ok");
	private JButton cancel = new JButton("Cancel");
	private Member temp_mem = new Member();

	public Password(Member m)
	{
		temp_mem = m;
		Container c = getContentPane();
		c.setLayout(new BorderLayout(5, 10));

		JPanel upper = new JPanel();
		upper.setLayout(new GridLayout(4, 2));
		upper.add(new JLabel("Member ID: "));
		upper.add(new JLabel(""+temp_mem.getID()));
		upper.add(new JLabel("Old Password: "));
		upper.add(oldPassword);
		upper.add(new JLabel("New Password: "));
		upper.add(newPassword);
		upper.add(new JLabel("Confirm Password: "));
		upper.add(confirmPassword);

		JPanel below = new JPanel();
		below.setLayout(new FlowLayout());
		below.add(ok);
		below.add(cancel);

		c.add(upper,BorderLayout.CENTER);
		c.add(below,BorderLayout.SOUTH);

		ok.addActionListener(this);
		cancel.addActionListener(this);

		setTitle("Member Profile");
		setLocation(200, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == ok)
		{
			if (!oldPassword.getText().equals(temp_mem.getPassword()))
			{
				JOptionPane.showMessageDialog(null, "Your Old Password does not match!");
			}
			else
			{
				if (!newPassword.getText().equals(confirmPassword.getText()))
				{
					JOptionPane.showMessageDialog(null, "Your New Password does not match with Confirm Password!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Your Password have been changed!");
					SQL link = new SQL();
					temp_mem.setPassword(link.updatePassword(temp_mem.getID(), newPassword.getText()));

					setVisible(false);
					new Profile(temp_mem);
				}
			}
		}

		else if (e.getSource() == cancel)
		{
			setVisible(false);
			new Profile(temp_mem);
		}
	}


}
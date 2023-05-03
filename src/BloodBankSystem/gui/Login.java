package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BloodBankSystem.dbtask.DatabaseOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtuserid;
	private JPasswordField txtuserpass;
	private JRadioButton rdworker,rdowner;
	private final ButtonGroup role = new ButtonGroup();
	private JButton btnsubmit;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//System.out.println("Hello I am main");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(new Color(255, 255, 255));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		con=DatabaseOperation.openConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/BloodBankSystem/Images/login.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("User ID");
		label.setFont(new Font("Calibri", Font.BOLD, 30));
		label.setBounds(51, 45, 107, 41);
		contentPane.add(label);
		
		txtuserid = new JTextField();
		txtuserid.setToolTipText("Enter ID Here ");
		txtuserid.setBounds(250, 49, 133, 33);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel_1.setBounds(43, 115, 133, 31);
		contentPane.add(lblNewLabel_1);
		
		txtuserpass = new JPasswordField();
		txtuserpass.setToolTipText("Enter Password Here");
		txtuserpass.setBounds(250, 113, 133, 33);
		contentPane.add(txtuserpass);
		txtuserpass.setColumns(10);
		
		rdworker = new JRadioButton("Worker");
		rdworker.setBackground(new Color(255, 250, 250));
		role.add(rdworker);
		rdworker.setFont(new Font("Calibri", Font.BOLD, 26));
		rdworker.setBounds(51, 164, 107, 44);
		contentPane.add(rdworker);
		
		rdowner = new JRadioButton("Owner");
		rdowner.setBackground(new Color(245, 245, 245));
		role.add(rdowner);
		rdowner.setFont(new Font("Calibri", Font.BOLD, 26));
		rdowner.setBounds(272, 170, 111, 33);
		contentPane.add(rdowner);
		
		
	    btnsubmit = new JButton("Submit");
	    btnsubmit.setIcon(new ImageIcon(Login.class.getResource("/BloodBankSystem/Images/submit1.png")));
		btnsubmit.addActionListener(this);// Register the Listener
		btnsubmit.setBackground(new Color(0, 153, 255));
		btnsubmit.setFont(new Font("Calibri", Font.BOLD, 28));
		btnsubmit.setBounds(138, 211, 150, 41);
		contentPane.add(btnsubmit);
	}

	@Override
public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==btnsubmit)
		{
		String id=txtuserid.getText().trim();//fetch the value from TextField
		char[] pwd=txtuserpass.getPassword();
		String pass=String.valueOf(pwd).trim();// converts char array into array
//		System.out.println("Button is Clicked");
		String user_role="";
		if(rdworker.isSelected())
			user_role=rdworker.getText();
		if(rdowner.isSelected())
			user_role=rdowner.getText();
		
	
		if(id.isEmpty() || pass.isEmpty() || user_role.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "All Fields are mandatory");
		}
		else
		{
			if(user_role.equalsIgnoreCase("worker"))
			{
				PreparedStatement ps = null;
				ResultSet rs = null;
				String strsql="select * from workerdetails where WorkerID=? and Password=?";
				try {
					ps=con.prepareStatement(strsql);
					ps.setString(1, id);
					ps.setString(2, pass);
					
				    rs=ps.executeQuery();
					if(rs.next())
					{
						WorkerFrame wr=new WorkerFrame();
						wr.setVisible(true);
						this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(this, "Invalid WorkerId/Password");
						
					}
				}
				catch(SQLException se)
					{
						se.printStackTrace();
					}
					finally {
						try {
							if(ps!=null)
								ps.close();
							if(rs!=null)
								rs.close();
						}
						catch(SQLException se)
						{
							se.printStackTrace();
						}		
					}
			}
			if(user_role.equalsIgnoreCase("owner"))
			{	
		    if(id.equalsIgnoreCase("owner@123") && pass.equals("owner") )
			{
			OwnerFrame or=new OwnerFrame();
			or.setVisible(true);
			this.dispose();
			}
		    else {
				JOptionPane.showMessageDialog(this, "Invalid Owner Id/Password");
			}
			}
		}
		
	}
	
		
	}
}
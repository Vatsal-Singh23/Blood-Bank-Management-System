package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

import BloodBankSystem.dbtask.DatabaseOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Update_Donor_Receiver extends JFrame implements ActionListener,ItemListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtphone;
	private JTextArea txtaddress;
	private JComboBox<String>cmbemail;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Donor_Receiver frame = new Update_Donor_Receiver();
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
	
	
	public void populateCombo()
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String strsql="select Email from donor_receiverdetails";//to fetch all the records
		try {
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next())// move the cursor till last
				
			{
				String email=rs.getString("Email");//to fetch data rom column course_name
				cmbemail.addItem(email);// add data into combobox
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
	public Update_Donor_Receiver() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update_Donor_Receiver.class.getResource("/BloodBankSystem/Images/update.png")));
		setTitle("Update Donor/Receiver");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email ID");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(37, 18, 89, 35);
		contentPane.add(lblNewLabel);
		
		cmbemail = new JComboBox();
		cmbemail.setFont(new Font("Calibri", Font.BOLD, 14));
		cmbemail.setModel(new DefaultComboBoxModel(new String[] {"Select Email ID"}));
		populateCombo();
		cmbemail.setBounds(204, 17, 160, 35);
		cmbemail.addItemListener(this);
		contentPane.add(cmbemail);
		
		JLabel lblNewLabel_1 = new JLabel("Phone No.");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(37, 92, 105, 35);
		contentPane.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(204, 83, 160, 35);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_2.setBounds(37, 160, 89, 40);
		contentPane.add(lblNewLabel_2);
		
	    txtaddress = new JTextArea();
		txtaddress.setBounds(204, 147, 160, 46);
		contentPane.add(txtaddress);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setBackground(new Color(0, 153, 255));
		btnupdate.setIcon(new ImageIcon(Update_Donor_Receiver.class.getResource("/BloodBankSystem/Images/update.png")));
		btnupdate.setBounds(94, 211, 105, 33);
		contentPane.add(btnupdate);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updateData();
		
	}
	
	public void updateData()
	{
		//System.out.println("Button is being clicked");
		String email=(String)cmbemail.getSelectedItem();
		
		String phone=txtphone.getText();
		String address=txtaddress.getText();
		
		if(phone.isEmpty()||address.isEmpty()||email.equalsIgnoreCase("Select EmailID")) 
		{
			JOptionPane.showMessageDialog(this,"Please Select/Enter Appropriate Data");
			
		}
		
		else {
			PreparedStatement ps=null;
			String strupdate="update donor_receiverdetails set Address=?,PhoneNo=? where Email=?";
			
			try {
				
			
			ps=con.prepareStatement(strupdate);
			ps.setString(1,address);
			ps.setString(2, phone);
			ps.setString(3,email);
			
			int status=ps.executeUpdate();//insert/update/delete
			if(status>0)
			{
				ImageIcon ic = new ImageIcon(Update_Donor_Receiver.class.getResource("/BloodBankSystem/Images/update.png"));
				JOptionPane.showMessageDialog(this,"Details Updated Successfully","Updation",JOptionPane.INFORMATION_MESSAGE,ic);
			}
			
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			finally{
				try {
					if(ps!=null)
						ps.close();
					
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
			
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		int state=e.getStateChange();
		//System.out.println(state);
		String email=(String)cmbemail.getSelectedItem();//fetch the data from combobox
		
		
		if(state==1)
		{
			PreparedStatement ps=null;
			ResultSet rs=null;
			String strsql="select * from donor_receiverdetails where Email=?";
			
			try {
				ps=con.prepareStatement(strsql);
				ps.setString(1,email);
				rs=ps.executeQuery();
				if(rs.next())
				{
					String address=rs.getString("Address");//fetching data from column
					txtaddress.setText(address);//setting the value in textbox
					
					txtphone.setText(rs.getString("PhoneNo"));//fetching the value and setting the textbox
				}
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			finally{
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
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	    DatabaseOperation.closeConnection();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		   if(e.getSource()==txtphone)
				
		     {
		     char c=e.getKeyChar();

		     //System.out.println(c);



			if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER)
					|| (c==KeyEvent.VK_TAB)
					||(Character.isDigit(c))))
			{
				e.consume();
				JOptionPane.showMessageDialog(this,"Phone Number requires only Numbers","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
		     }

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	
}

package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

import BloodBankSystem.dbtask.DatabaseOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Add_Donor_Receiver extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txtage;
	private JTextField txtaddress;
	private JComboBox<String>cmbtype;
	private JComboBox<String>cmbgender;
	private JComboBox<String>cmbbloodgroup;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Donor_Receiver frame = new Add_Donor_Receiver();
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
	public Add_Donor_Receiver() {
		con=DatabaseOperation.openConnection();//getting the reference of bloodbanksystemmis
		this.addWindowListener(this);// registering the resource with listener
		setIconImage(Toolkit.getDefaultToolkit().getImage(Add_Donor_Receiver.class.getResource("/BloodBankSystem/Images/add.png")));
		setTitle("Add Donor/Receiver");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(45, 15, 67, 17);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setBounds(226, 11, 125, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(226, 42, 125, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(226, 73, 125, 19);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtage = new JTextField();
		txtage.addKeyListener(this);
		txtage.setBounds(226, 134, 125, 18);
		contentPane.add(txtage);
		txtage.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setBounds(226, 208, 125, 29);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(43, 43, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone No.");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_2.setBounds(39, 73, 73, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_3.setBounds(45, 132, 49, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_4.setBounds(45, 162, 67, 17);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Blood Group");
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_5.setBounds(45, 189, 84, 21);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Address");
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_6.setBounds(45, 220, 67, 17);
		contentPane.add(lblNewLabel_6);
		
		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(this);
		btnadd.setIcon(null);
		btnadd.setBackground(new Color(0, 153, 255));
		btnadd.setBounds(136, 235, 73, 17);
		contentPane.add(btnadd);
		
	    cmbtype = new JComboBox();
	    cmbtype.setFont(new Font("Calibri", Font.BOLD, 12));
		cmbtype.setModel(new DefaultComboBoxModel(new String[] {"Select Type", "Donor", "Receiver"}));
		cmbtype.setBounds(226, 103, 125, 26);
		contentPane.add(cmbtype);
		
	    cmbgender = new JComboBox();
	    cmbgender.setFont(new Font("Calibri", Font.BOLD, 12));
		cmbgender.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Male", "Female"}));
		cmbgender.setBounds(226, 158, 125, 21);
		contentPane.add(cmbgender);
		
	    cmbbloodgroup = new JComboBox();
	    cmbbloodgroup.setFont(new Font("Calibri", Font.BOLD, 12));
		cmbbloodgroup.setModel(new DefaultComboBoxModel(new String[] {"Select BloodGroup", "A+", "AB+", "B+", "O+", "A-", "AB-", "B-", "O-"}));
		cmbbloodgroup.setBounds(226, 184, 125, 21);
		contentPane.add(cmbbloodgroup);
	}

	
	
	public void actionPerformed(ActionEvent e) {
		String name=txtname.getText().trim();
		String email=txtemail.getText().trim();
		String phone=txtphone.getText().trim();
		String type=(String)cmbtype.getSelectedItem();//fetch the data from combobox
		String age=txtage.getText().trim();
		String gender=(String)cmbgender.getSelectedItem();
		String bloodgroup=(String)cmbbloodgroup.getSelectedItem();
		String address=txtaddress.getText().trim();
	
		
		if(name.isEmpty()||email.isEmpty()||phone.isEmpty()||type.equalsIgnoreCase("Select Type")||age.isEmpty()||gender.equalsIgnoreCase("Select Gender")||bloodgroup.equalsIgnoreCase("Select BloodGroup")||address.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "All the Fields are mandatory",
					"MandatoryFields",JOptionPane.ERROR_MESSAGE);
			
		}
		
		else {
			PreparedStatement ps=null;// creating communication channel
			try {
				String strinsert="insert into donor_receiverdetails(PhoneNo, Name, Email, Address, Age, Gender, BloodGroup, Type)values(?,?,?,?,?,?,?,?)";
				ps=con.prepareStatement(strinsert);//passes the query to 
				//dbms ->parser-parse query compile the query 
				//and reference of the compiled query will be stored into ps
				
				ps.setString(1, phone);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, address);
				ps.setInt(5,Integer.parseInt(age));
				ps.setString(6, gender);
				ps.setString(7, bloodgroup);
				ps.setString(8, type);
				System.out.println(ps);
				int status=ps.executeUpdate();// insert/update/delete
				if(status>0)
				{
					JOptionPane.showMessageDialog(this, "Details Added Successfully");
					txtname.setText("");
					txtemail.setText("");
					txtphone.setText("");
					cmbtype.removeAllItems();
					cmbtype.addItem("Select Type");
					txtage.setText("");
					cmbgender.removeAllItems();
					cmbgender.addItem("Select Gender");
					cmbbloodgroup.removeAllItems();
					cmbbloodgroup.addItem("Select BloodGroup");
					txtaddress.setText("");
				}
				
				
			}// try close
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			finally {
				if(ps!=null)
					try {
						ps.close();
					} catch (SQLException se) {
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
		// TODO Auto-generated method stub
		
		
		
    if(e.getSource()==txtage)
			
		{
	char c=e.getKeyChar();
	
	//System.out.println(c);
	
	
	
			if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER)
					|| (c==KeyEvent.VK_TAB)
					||(Character.isDigit(c))))
			{
				e.consume();
				JOptionPane.showMessageDialog(this,"Age requires only Numbers","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}

			
		}

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
		

    if(e.getSource()==txtname)
	
    {
    char c=e.getKeyChar();

     //System.out.println(c);



	if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER)
			|| (c==KeyEvent.VK_TAB)
			||(Character.isAlphabetic(c))))
	{
		e.consume();
		JOptionPane.showMessageDialog(this,"Name requires only Alphabets","MANDATORY",JOptionPane.WARNING_MESSAGE);
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

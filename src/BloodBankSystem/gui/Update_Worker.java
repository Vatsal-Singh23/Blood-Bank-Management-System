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

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Update_Worker extends JFrame implements ActionListener,ItemListener,WindowListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtphone;
	private JLabel lblNewLabel_2;
	private JTextArea txtaddress;
	private JComboBox<String>cmbid;
	private JButton btnupdate;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Worker frame = new Update_Worker();
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
		
		String strsql="select WorkerID from workerdetails";//to fetch all the records
		try {
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next())// move the cursor till last
				
			{
				String name=rs.getString("WorkerID");//to fetch data rom column course_name
				cmbid.addItem(name);// add data into combobox
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
	public Update_Worker() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update_Worker.class.getResource("/BloodBankSystem/Images/update.png")));
		setTitle("Update Worker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Worker ID");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(58, 44, 106, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phone No.");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(60, 108, 89, 33);
		contentPane.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(231, 113, 106, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_2.setBounds(58, 173, 91, 25);
		contentPane.add(lblNewLabel_2);
		
		txtaddress = new JTextArea();
		txtaddress.setBounds(231, 172, 117, 33);
		contentPane.add(txtaddress);
		
		btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setBackground(new Color(0, 153, 255));
		btnupdate.setIcon(new ImageIcon(Update_Worker.class.getResource("/BloodBankSystem/Images/update.png")));
		btnupdate.setFont(new Font("Calibri", Font.BOLD, 18));
		btnupdate.setBounds(128, 209, 123, 43);
		contentPane.add(btnupdate);
		
	    cmbid = new JComboBox();
	    cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select ID"}));
		populateCombo();
		cmbid.setBounds(231, 44, 106, 25);
		cmbid.addItemListener(this);//register the combobox with listener
		contentPane.add(cmbid);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateData();
		
	}
	
	
	public void updateData()
	{
		//System.out.println("Button is being clicked");
		String id=(String)cmbid.getSelectedItem();
		
		String phone=txtphone.getText();
		String address=txtaddress.getText();
		
		if(phone.isEmpty()||address.isEmpty()||id.equalsIgnoreCase("Select ID")) 
		{
			JOptionPane.showMessageDialog(this,"Please Select/Enter Appropriate Data");
			
		}
		
		else {
			PreparedStatement ps=null;
			String strupdate="update workerdetails set Address=?,PhoneNo=? where WorkerID=?";
			
			try {
				
			
			ps=con.prepareStatement(strupdate);
			ps.setString(1,address);
			ps.setString(2, phone);
			ps.setString(3,id);
			
			int status=ps.executeUpdate();//insert/update/delete
			if(status>0)
			{
				ImageIcon ic = new ImageIcon(Update_Worker.class.getResource("/BloodBankSystem/Images/update.png"));
				JOptionPane.showMessageDialog(this,"Worker Details Updated Successfully","Updation",JOptionPane.INFORMATION_MESSAGE,ic);
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

	
     public void itemStateChanged(ItemEvent e) {
		
		int state=e.getStateChange();
		//System.out.println(state);
		String id=(String)cmbid.getSelectedItem();//fetch the data from combobox
		
		
		if(state==1)
		{
			PreparedStatement ps=null;
			ResultSet rs=null;
			String strsql="select * from workerdetails where WorkerID=?";
			
			try {
				ps=con.prepareStatement(strsql);
				ps.setString(1,id);
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

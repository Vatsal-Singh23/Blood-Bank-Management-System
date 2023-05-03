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
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Update_BloodBags extends JFrame implements ActionListener,ItemListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtcount;
	private JComboBox<String>cmbbloodgroup;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_BloodBags frame = new Update_BloodBags();
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
		
		String strsql="select Bloodgroup from bloodbagdetails";//to fetch all the records
		try {
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next())// move the cursor till last
				
			{
				String bloodgroup=rs.getString("Bloodgroup");//to fetch data rom column course_name
				cmbbloodgroup.addItem(bloodgroup);// add data into combobox
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
	public Update_BloodBags() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update_BloodBags.class.getResource("/BloodBankSystem/Images/update.png")));
		setTitle("Update Blood Bags");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Blood Group");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setBounds(32, 33, 103, 28);
		contentPane.add(lblNewLabel);
		
	    cmbbloodgroup = new JComboBox();
		cmbbloodgroup.setModel(new DefaultComboBoxModel(new String[] {"Select BloodGroup"}));
		populateCombo();
		cmbbloodgroup.setBounds(216, 33, 138, 28);
		cmbbloodgroup.addItemListener(this);
		contentPane.add(cmbbloodgroup);
		
		JLabel lblNewLabel_1 = new JLabel("No. of Bags");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1.setBounds(32, 125, 103, 28);
		contentPane.add(lblNewLabel_1);
		
		txtcount = new JTextField();
		txtcount.addKeyListener(this);
		txtcount.setBounds(216, 125, 138, 28);
		contentPane.add(txtcount);
		txtcount.setColumns(10);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setIcon(new ImageIcon(Update_BloodBags.class.getResource("/BloodBankSystem/Images/update.png")));
		btnupdate.setBackground(new Color(0, 153, 255));
		btnupdate.setFont(new Font("Calibri", Font.BOLD, 16));
		btnupdate.setBounds(135, 207, 119, 28);
		contentPane.add(btnupdate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	        updateData();
		
	}
	
	public void updateData()
	{
		
		String bloodgroup=(String)cmbbloodgroup.getSelectedItem();
		
		String count=txtcount.getText();
		
		if(count.isEmpty()||bloodgroup.equalsIgnoreCase("Select BloodGroup")) 
		{
			JOptionPane.showMessageDialog(this,"Please Select/Enter Appropriate Data");
			
		}
		
		else {
			PreparedStatement ps=null;
			String strupdate="update bloodbagdetails set NoOfBags=? where Bloodgroup=?";
			
			try {
				
			
			ps=con.prepareStatement(strupdate);
			ps.setInt(1, Integer.parseInt(count));
			ps.setString(2, bloodgroup);

			
			int status=ps.executeUpdate();//insert/update/delete
			if(status>0)
			{
				JOptionPane.showMessageDialog(this,"Blood Bags Details Updated Successfully","Updation",JOptionPane.INFORMATION_MESSAGE);
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
		String bloodgroup=(String)cmbbloodgroup.getSelectedItem();//fetch the data from combobox
		
		
		if(state==1)
		{
			PreparedStatement ps=null;
			ResultSet rs=null;
			String strsql="select * from bloodbagdetails where Bloodgroup=?";
			
			try {
				ps=con.prepareStatement(strsql);
				ps.setString(1,bloodgroup);
				rs=ps.executeQuery();
				if(rs.next())
				{
					txtcount.setText(rs.getString("NoOfBags"));//fetching the value and setting the textbox
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
		   if(e.getSource()==txtcount)
				
		     {
		     char c=e.getKeyChar();

		     //System.out.println(c);



			if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER)
					|| (c==KeyEvent.VK_TAB)
					||(Character.isDigit(c))))
			{
				e.consume();
				JOptionPane.showMessageDialog(this,"Blood Bags requires only Numbers","MANDATORY",JOptionPane.WARNING_MESSAGE);
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

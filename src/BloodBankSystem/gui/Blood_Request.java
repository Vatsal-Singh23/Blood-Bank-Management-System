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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

import BloodBankSystem.dbtask.DatabaseOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Blood_Request extends JFrame implements ActionListener,WindowListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtphone;
	private JTextField txtbloodgroup;
	private JTextField txtbags;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Blood_Request frame = new Blood_Request();
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
	public Blood_Request() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);
		setTitle("Blood Request");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Blood_Request.class.getResource("/BloodBankSystem/Images/request.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone No.");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(24, 31, 80, 20);
		contentPane.add(lblNewLabel);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(160, 29, 96, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtbloodgroup = new JTextField();
		txtbloodgroup.setBounds(160, 88, 96, 20);
		contentPane.add(txtbloodgroup);
		txtbloodgroup.setColumns(10);
		
		txtbags = new JTextField();
		txtbags.addKeyListener(this);
		txtbags.setBounds(160, 151, 96, 20);
		contentPane.add(txtbags);
		txtbags.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(this);
		btnsearch.setBackground(new Color(0, 153, 255));
		btnsearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnsearch.setIcon(new ImageIcon(Blood_Request.class.getResource("/BloodBankSystem/Images/search.png")));
		btnsearch.setBounds(298, 23, 108, 33);
		contentPane.add(btnsearch);
		
		JLabel lblNewLabel_1 = new JLabel("Blood Group");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBounds(24, 90, 96, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("No. of Bags");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_2.setBounds(24, 153, 96, 20);
		contentPane.add(lblNewLabel_2);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setBackground(new Color(0, 153, 255));
		btnupdate.setIcon(new ImageIcon(Blood_Request.class.getResource("/BloodBankSystem/Images/update.png")));
		btnupdate.setFont(new Font("Calibri", Font.BOLD, 14));
		btnupdate.setBounds(124, 199, 108, 33);
		contentPane.add(btnupdate);
	}
	@Override
	
		public void actionPerformed(ActionEvent e) {
			
			String caption=e.getActionCommand();
			if(caption.equalsIgnoreCase("Search"))
			searchReceiver();
			
			
			if(caption.equalsIgnoreCase("Update"))
				
			bloodReceiving();
			
		}
			
	     String ph = null;
		
		public void bloodReceiving()
		{
			String bgbags=txtbags.getText();
			String  bloodgroup=txtbloodgroup.getText();
			if(bgbags.isEmpty() || bloodgroup.isEmpty())//check for emptiness
			{ 
				JOptionPane.showMessageDialog(this, "Please Insert Appropriate Data");
			}
			else {
				if(Integer.parseInt(bgbags)>5)
				{
					JOptionPane.showMessageDialog(this,"cannot exceed more than 5 bags");
				}
				else {
				PreparedStatement psinsert,psupdate=null;
				
				String strinsert="insert into blood_requestdetails(PhoneNo, Date, NoOfBags, BloodGroup)values(?,?,?,?)";
				
				try {
//					java.util.Date d=new java.util.Date();
//					long dt=d.getTime();
//					java.sql.Date sd=new java.sql.Date(dt);
					
					
					psinsert=con.prepareStatement(strinsert);
					psinsert.setString(1, ph);
					psinsert.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
					psinsert.setInt(3, Integer.parseInt(bgbags));
					psinsert.setString(4, bloodgroup);
					
					
				int status=	psinsert.executeUpdate();
				
				
				String strupdate="update bloodbagdetails set noofbags=noofbags-? where bloodgroup=?";
				psupdate=con.prepareStatement(strupdate);
				psupdate.setInt(1, Integer.parseInt(bgbags));
				psupdate.setString(2, bloodgroup);
				System.out.println(psupdate);
			    int status1=psupdate.executeUpdate();
			
				if(status>0 && status1>0)
				{
					JOptionPane.showMessageDialog(this,"Data added successfully");
				}
				
					
				}
				
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				finally {
					if(psupdate!=null)
						try {
							psupdate.close();
						} catch (SQLException se) {
							se.printStackTrace();
						}
				}
			}
			}
				
			}
			
		

		
		public void searchReceiver()
		{
			 ph=txtphone.getText();
			
			if(ph.isEmpty())
			{

				JOptionPane.showMessageDialog(this,"Please Select/Enter Appropriate Data");	
			}
			
			else {
				
				PreparedStatement ps=null;
				ResultSet rs=null;
				
				
				String strsql="select bloodgroup from donor_receiverdetails where phoneno=? and type='Receiver'";
				try {
					
					
					ps=con.prepareStatement(strsql);
					ps.setString(1,ph);
					rs=ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this, "Receiver Exists you can provide blood");	
					}
                    else {
						JOptionPane.showMessageDialog(this, "NO such Phone Number Exists");
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
		   
		   
		   if(e.getSource()==txtbags)
				
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

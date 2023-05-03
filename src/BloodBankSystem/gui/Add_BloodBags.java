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
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Add_BloodBags extends JFrame implements ActionListener,WindowListener,KeyListener{

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
					Add_BloodBags frame = new Add_BloodBags();
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
	public Add_BloodBags() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);// registering the resource with listener
		setIconImage(Toolkit.getDefaultToolkit().getImage(Add_BloodBags.class.getResource("/BloodBankSystem/Images/add.png")));
		setTitle("Add Blood Bags");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Blood Group");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(37, 56, 112, 23);
		contentPane.add(lblNewLabel);
		
	    cmbbloodgroup = new JComboBox();
	    cmbbloodgroup.setFont(new Font("Calibri", Font.BOLD, 12));
		cmbbloodgroup.setModel(new DefaultComboBoxModel(new String[] {"Select BloodGroup", "A+", "AB+", "B+", "O+", "A-", "AB-", "B-", "O-"}));
		cmbbloodgroup.setToolTipText("Select Blood Group");
		cmbbloodgroup.setBounds(226, 52, 131, 27);
		contentPane.add(cmbbloodgroup);
		
		JLabel lblNewLabel_1 = new JLabel("No. of Bags");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBounds(37, 134, 96, 23);
		contentPane.add(lblNewLabel_1);
		
		txtcount = new JTextField();
		txtcount.addKeyListener(this);
		txtcount.setBounds(226, 133, 131, 20);
		contentPane.add(txtcount);
		txtcount.setColumns(10);
		
		JButton btnsubmit = new JButton("Add");
		btnsubmit.setIcon(new ImageIcon(Add_BloodBags.class.getResource("/BloodBankSystem/Images/add.png")));
		btnsubmit.addActionListener(this);
		btnsubmit.setBackground(new Color(0, 153, 255));
		btnsubmit.setFont(new Font("Calibri", Font.BOLD, 18));
		btnsubmit.setBounds(133, 201, 112, 33);
		contentPane.add(btnsubmit);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String bloodgroup=(String)cmbbloodgroup.getSelectedItem();//fetch the data from combobox
		String count=txtcount.getText().trim();
		
		if(bloodgroup.equalsIgnoreCase("Select BloodGroup")||count.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "All the Fields are mandatory",
					"MandatoryFields",JOptionPane.ERROR_MESSAGE);
			
		}
		
		else {
			PreparedStatement ps=null,pscheck=null,psupdate=null;// creating communication channel
			ResultSet rs=null;
			try {
				
				
				String strcheck="select bloodgroup from bloodbagdetails where bloodgroup=?";
				
				pscheck=con.prepareStatement(strcheck);
				
				pscheck.setString(1, bloodgroup);
				rs=pscheck.executeQuery();
				
				if(rs.next())
				{
					
					String strupdate="update bloodbagdetails set noofbags=noofbags+? where bloodgroup=?";
					
					psupdate=con.prepareStatement(strupdate);
					psupdate.setInt(1, Integer.parseInt(count));
					psupdate.setString(2, bloodgroup);
				int status=	psupdate.executeUpdate();
				if(status>0)
				{
					
					JOptionPane.showMessageDialog(this, "Blood Bags added successfully");
				}
					
				}
				else {
				
				String strinsert="insert into bloodbagdetails(Bloodgroup, NoOfBags)values(?,?)";
				ps=con.prepareStatement(strinsert);//passes the query to 
				//dbms ->parser-parse query compile the query 
				//and reference of the compiled query will be stored into ps
				
				ps.setString(1, bloodgroup);
				ps.setInt(2,Integer.parseInt(count));
				System.out.println(ps);
				int status=ps.executeUpdate();// insert/update/delete
				if(status>0)
				{
					JOptionPane.showMessageDialog(this, " Blood Bags Details Added Successfully");
					cmbbloodgroup.removeAllItems();
					cmbbloodgroup.addItem("Select BloodGroup");
					txtcount.setText("");
				}
				
				
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

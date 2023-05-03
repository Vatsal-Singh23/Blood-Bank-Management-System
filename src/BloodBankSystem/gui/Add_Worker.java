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

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class Add_Worker extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel txt;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtid;
	private JTextField txtpassword;
	private JTextArea txtaddress;
	private Connection con;// null
	private JTextField txtgender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Worker frame = new Add_Worker();
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
	public Add_Worker() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Add_Worker.class.getResource("/BloodBankSystem/Images/add.png")));
		con=DatabaseOperation.openConnection();//getting the reference of bloodbanksystemmis
		this.addWindowListener(this);// registering the resource with listener
		setTitle("Add Worker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		txt = new JPanel();
		txt.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(txt);
		txt.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setBounds(84, 76, 90, 23);
		txt.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setBounds(231, 76, 107, 23);
		txt.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Phone No.");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1.setBounds(84, 110, 90, 23);
		txt.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(231, 110, 107, 23);
		txt.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_2.setBounds(84, 144, 69, 23);
		txt.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_3.setBounds(84, 177, 75, 23);
		txt.add(lblNewLabel_3);
		
	    txtaddress = new JTextArea();
		txtaddress.setBounds(231, 177, 118, 36);
		txt.add(txtaddress);
		
		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(this);// Register the Listener
		btnadd.setBackground(new Color(0, 153, 255));
		btnadd.setForeground(new Color(0, 0, 0));
		btnadd.setFont(new Font("Calibri", Font.BOLD, 20));
		btnadd.setIcon(new ImageIcon(Add_Worker.class.getResource("/BloodBankSystem/Images/add.png")));
		btnadd.setBounds(124, 224, 107, 28);
		txt.add(btnadd);
		
		JLabel lblNewLabel_4 = new JLabel("Worker ID");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_4.setBounds(70, 11, 88, 26);
		txt.add(lblNewLabel_4);
		
		txtid = new JTextField();
		txtid.setBounds(231, 13, 107, 23);
		txt.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_5.setBounds(70, 45, 77, 20);
		txt.add(lblNewLabel_5);
		
		txtpassword = new JTextField();
		txtpassword.setBounds(231, 47, 107, 23);
		txt.add(txtpassword);
		txtpassword.setColumns(10);
		
		txtgender = new JTextField();
		txtgender.addKeyListener(this);
		txtgender.setBounds(231, 144, 107, 23);
		txt.add(txtgender);
		txtgender.setColumns(10);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Button is being Clicked");
		String id=txtid.getText().trim();
		String password=txtpassword.getText().trim();
		String name=txtname.getText().trim();
		String phone_number=txtphone.getText().trim();
		String gender=txtgender.getText().trim();
		String address=txtaddress.getText().trim();
	
		
		if(id.isEmpty()||password.isEmpty()||name.isEmpty()||phone_number.isEmpty()||gender.isEmpty()||address.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "All the Fields are mandatory",
					"MandatoryFields",JOptionPane.ERROR_MESSAGE);
			
		}
		
		else {
			PreparedStatement ps=null;// creating communication channel
			try {
				String strinsert="insert into workerdetails(WorkerID, Password, Name, PhoneNo, Gender, Address)values(?,?,?,?,?,?)";
				ps=con.prepareStatement(strinsert);//passes the query to 
				//dbms ->parser-parse query compile the query 
				//and reference of the compiled query will be stored into ps
				
				ps.setString(1, id);
				ps.setString(2, password);
				ps.setString(3, name);
				ps.setString(4, phone_number);
				ps.setString(5, gender);
				ps.setString(6, address);
				System.out.println(ps);
				int status=ps.executeUpdate();// insert/update/delete
				if(status>0)
				{
					JOptionPane.showMessageDialog(this, "Worker Details Added Successfully");
					txtid.setText("");
					txtpassword.setText("");
					txtname.setText("");
					txtphone.setText("");
					txtgender.setText("");
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
		if(e.getSource()==txtgender)
			
		{
		char c=e.getKeyChar();

		//System.out.println(c);



			if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER)
					|| (c==KeyEvent.VK_TAB)
					||(Character.isAlphabetic(c))))
			{
				e.consume();
				JOptionPane.showMessageDialog(this,"Gender requires only Alphabets","MANDATORY",JOptionPane.WARNING_MESSAGE);
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

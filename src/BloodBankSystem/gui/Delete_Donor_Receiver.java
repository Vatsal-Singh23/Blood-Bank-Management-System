package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

import BloodBankSystem.dbtask.DatabaseOperation;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Delete_Donor_Receiver extends JFrame implements ActionListener,WindowListener {

	private JPanel contentPane;
	private JTextField txtemail;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_Donor_Receiver frame = new Delete_Donor_Receiver();
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
	public Delete_Donor_Receiver() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Delete_Donor_Receiver.class.getResource("/BloodBankSystem/Images/delete.png")));
		setTitle("Delete Donor/Receiver");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtemail = new JTextField();
		txtemail.setBounds(235, 35, 141, 29);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setIcon(new ImageIcon(Delete_Donor_Receiver.class.getResource("/BloodBankSystem/Images/delete.png")));
		btndelete.setBackground(new Color(255, 51, 51));
		btndelete.setFont(new Font("Calibri", Font.BOLD, 20));
		btndelete.setBounds(155, 145, 121, 38);
		contentPane.add(btndelete);
		
		JLabel lblNewLabel = new JLabel("Email ID");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(31, 38, 93, 25);
		contentPane.add(lblNewLabel);
	}
	
	public void actionPerformed(ActionEvent e) {
		deleteData();
	}
	
	public void deleteData()
	{
		//System.out.println("Click");
		
		String email=txtemail.getText().trim();
		
		if(email.isBlank())
		{
			JOptionPane.showMessageDialog(this, "Please Enter Email ID","Deletion",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int choice=JOptionPane.showConfirmDialog(this, "Are You Sure to delete the Email ID "+email);
			//System.out.println(choice);
			
			PreparedStatement ps=null;
			
			if(choice==0)//status of yes button
			{
				String strdelete="delete from donor_receiverdetails where Email=?";
				try {
					ps=con.prepareStatement(strdelete);
					ps.setString(1,email);
					System.out.println(ps);
					int status=ps.executeUpdate();
					if(status>0)
					{
						JOptionPane.showMessageDialog(this, "Email "+email+ " Deleted Successfully");
						txtemail.setText("");
					}
					else {
						JOptionPane.showMessageDialog(this, "No Such Email ID Exists","Deletion Error",JOptionPane.ERROR_MESSAGE);
						txtemail.setText("");
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
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
			
			
		}// choice if
			else
			{
				txtemail.setText("");
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


}

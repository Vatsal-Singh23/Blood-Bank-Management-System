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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Delete_Worker extends JFrame implements ActionListener,WindowListener {

	private JPanel contentPane;
	private JTextField txtid;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_Worker frame = new Delete_Worker();
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
	public Delete_Worker() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);// registering the resource with listener
		setIconImage(Toolkit.getDefaultToolkit().getImage(Delete_Worker.class.getResource("/BloodBankSystem/Images/delete.png")));
		setTitle("Delete Worker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Worker ID");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(48, 59, 114, 30);
		contentPane.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.setBounds(217, 55, 155, 36);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setBackground(new Color(255, 0, 0));
		btndelete.setIcon(new ImageIcon(Delete_Worker.class.getResource("/BloodBankSystem/Images/delete.png")));
		btndelete.setFont(new Font("Calibri", Font.BOLD, 24));
		btndelete.setBounds(149, 159, 142, 39);
		contentPane.add(btndelete);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		deleteData();
		
	}
	
	public void deleteData()
	{
		//System.out.println("Click");
		
		String id=txtid.getText().trim();
		
		if(id.isBlank())
		{
			JOptionPane.showMessageDialog(this, "Please Enter Worker ID","Deletion",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int choice=JOptionPane.showConfirmDialog(this, "Are You Sure to delete the Worker "+id);
			//System.out.println(choice);
			
			PreparedStatement ps=null;
			
			if(choice==0)//status of yes button
			{
				String strdelete="delete from workerdetails where WorkerID=?";
				try {
					ps=con.prepareStatement(strdelete);
					ps.setString(1,id);
					System.out.println(ps);
					int status=ps.executeUpdate();
					if(status>0)
					{
						JOptionPane.showMessageDialog(this, "Worker "+id+ " Deleted Successfully");
						txtid.setText("");
					}
					else {
						JOptionPane.showMessageDialog(this, "No Such WorkerID Exists","Deletion Error",JOptionPane.ERROR_MESSAGE);
						txtid.setText("");
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
				txtid.setText("");
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

package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import BloodBankSystem.dbtask.DatabaseOperation;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_Receivers extends JFrame  implements ActionListener,WindowListener{

	private JPanel contentPane;
	private Connection con;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Receivers frame = new View_Receivers();
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
	public View_Receivers() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);// registering the resource with listener
		setIconImage(Toolkit.getDefaultToolkit().getImage(View_Receivers.class.getResource("/BloodBankSystem/Images/man.png")));
		setTitle("View Receivers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 83, 932, 513);
		contentPane.add(scrollPane);
		
        table = new JTable();
		JTableHeader header=table.getTableHeader();// for table headings
		header.setBackground(Color.black);
		header.setForeground(Color.white);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,12));
		
		table.setForeground(Color.blue);
		table.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		scrollPane.setViewportView(table);
		showReceivers();
	}
	
	public void showReceivers()
	{
		
		
		String strsql="select * from donor_receiverdetails where type='Receiver'";
		
       PreparedStatement ps=null;
       ResultSet rs=null;

     try {
	
	
	ps=con.prepareStatement(strsql);
	
	rs=ps.executeQuery();
	
	table.setModel(DbUtils.resultSetToTableModel(rs));
     }
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		
		
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


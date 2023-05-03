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
import javax.swing.border.LineBorder;

public class View_BloodBags extends JFrame implements ActionListener,WindowListener{
	private Connection con;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_BloodBags frame = new View_BloodBags();
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
	public View_BloodBags() {
		con=DatabaseOperation.openConnection();
		this.addWindowListener(this);// registering the resource with listener
		setIconImage(Toolkit.getDefaultToolkit().getImage(View_BloodBags.class.getResource("/BloodBankSystem/Images/request.png")));
		setTitle("View BloodBags");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 11, 368, 241);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		JTableHeader header=table.getTableHeader();// for table headings
		header.setBackground(Color.black);
		header.setForeground(Color.white);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,12));
		
		table.setForeground(Color.blue);
		table.setFont(new Font("Calibri", Font.PLAIN, 12)); 
		
		scrollPane.setViewportView(table);
		showBloodBags();
	
	}
	
	public void showBloodBags()
	{
		String strsql="select * from bloodbagdetails";
		
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
